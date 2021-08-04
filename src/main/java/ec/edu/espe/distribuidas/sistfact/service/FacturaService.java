/*
 * Copyright (c) 2021 Hendrix.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Hendrix - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.sistfact.service;

import ec.edu.espe.distribuidas.sistfact.dao.FacturaRepository;
import ec.edu.espe.distribuidas.sistfact.dao.ImpuestoPorcentajeRepository;
import ec.edu.espe.distribuidas.sistfact.dao.ProductoRepository;
import ec.edu.espe.distribuidas.sistfact.model.Factura;
import ec.edu.espe.distribuidas.sistfact.model.FacturaDetalle;
import ec.edu.espe.distribuidas.sistfact.model.FacturaDetalleImpuesto;
import ec.edu.espe.distribuidas.sistfact.model.FacturaImpuesto;
import ec.edu.espe.distribuidas.sistfact.model.ImpuestoPorcentaje;
import ec.edu.espe.distribuidas.sistfact.model.Producto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hendrix
 */
@Service
@Slf4j
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ProductoRepository productoRepository;
    private final ImpuestoPorcentajeRepository impuestoPorcentajeRepository;

    public FacturaService(FacturaRepository facturaRepository, ProductoRepository productoRepository,
            ImpuestoPorcentajeRepository impuestoPorcentajeRepository) {
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
        this.impuestoPorcentajeRepository = impuestoPorcentajeRepository;
    }

    public Factura obtenerPorNumeroAutorizacion(String numeroAutorizacion) {
        Factura facturaOpt = this.facturaRepository.findByNumeroAutorizacion(numeroAutorizacion);
        return facturaOpt;
    }

    public List<Factura> obtenerPorFechas(Date fechaInicio, Date fechaFin) {
        LocalDateTime ldInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        LocalDateTime ldFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(23, 59, 59);
        log.info("Va a buscar facturas desde: {} hasta: {}", ldInicio, ldFin);
        if (ldInicio.isBefore(ldFin)) {
            return this.facturaRepository.findByFechaBetween(Timestamp.valueOf(ldInicio), Timestamp.valueOf(ldFin));
        } else {
            log.error("Error en las fechas recibidas: fechaInicio: {}, fechaFin: {}", fechaInicio, fechaFin);
            throw new RuntimeException("Fechas invalidas");
        }
    }

    @Transactional
    public Factura crearFactura(Factura factura) {
        BigDecimal subtotal = new BigDecimal("0.00");
        BigDecimal impuestos = new BigDecimal("0.00");
        for (FacturaDetalle detalle : factura.getDetalles()) {
            Producto producto = this.productoRepository.findById(detalle.getPk().getCodigoProducto()).get();
            detalle.setFactura(factura);
            detalle.setNombreProducto(producto.getNombre());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(detalle.getCantidad().multiply(detalle.getPrecioUnitario()));
            producto.setExistencia(producto.getExistencia().subtract(detalle.getCantidad()));
            if ("S".equals(producto.getIva())) {
                List<ImpuestoPorcentaje> impuestosPorcentaje = this.impuestoPorcentajeRepository
                        .findByPkCodigoImpuestoAndEstadoOrderByPkPorcentaje("IVA", "ACT");
                ImpuestoPorcentaje ipTemp = null;
                for (ImpuestoPorcentaje ip : impuestosPorcentaje) {
                    if (ip.getPk().getPorcentaje().floatValue() >= 0.1) {
                        ipTemp = ip;
                    }
                }
                FacturaDetalleImpuesto fdi = new FacturaDetalleImpuesto(ipTemp.getPk().getCodigoImpuesto(),
                        ipTemp.getPk().getPorcentaje(), null, producto.getCodigo());
                fdi.setValor(detalle.getSubtotal().multiply(fdi.getPk().getPorcentaje()));
                fdi.setValor(fdi.getValor().divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_UP));
                impuestos = impuestos.add(fdi.getValor());
            }
            subtotal = subtotal.add(detalle.getSubtotal());
        }
        factura.setFecha(new Date());
        factura.setNumeroAutorizacion(this.numeroAutorizacion(factura));
        factura.setSubtotal(subtotal);
        factura.setTotal(subtotal.add(impuestos));
        FacturaImpuesto facturaImpuesto = new FacturaImpuesto(null, "IVA", new BigDecimal("12.00"));
        facturaImpuesto.setValor(impuestos);
        facturaImpuesto.setFactura(factura);
        List<FacturaImpuesto> imps = new ArrayList<>();
        imps.add(facturaImpuesto);
        factura.setImpuestos(imps);
        log.info("Va a registrar la factrura: {}", factura);
        this.facturaRepository.save(factura);
        return factura;
    }

    public String numeroAutorizacion(Factura factura) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
        StringBuilder sb = new StringBuilder(sdf.format(factura.getFecha()));
        sb.append(factura.getCodigoEstablecimiento());
        sb.append(factura.getPuntoEmision());
        sb.append(String.format("%09d", factura.getSecuencial()));
        return sb.toString();
    }
}
