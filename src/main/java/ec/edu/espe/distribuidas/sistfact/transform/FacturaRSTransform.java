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
package ec.edu.espe.distribuidas.sistfact.transform;

import ec.edu.espe.distribuidas.sistfact.dto.FacturaDetalleRS;
import ec.edu.espe.distribuidas.sistfact.dto.FacturaImpuestoRS;
import ec.edu.espe.distribuidas.sistfact.dto.FacturaRS;
import ec.edu.espe.distribuidas.sistfact.model.Factura;
import ec.edu.espe.distribuidas.sistfact.model.FacturaDetalle;
import ec.edu.espe.distribuidas.sistfact.model.FacturaImpuesto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hendrix
 */
public class FacturaRSTransform {

    public static FacturaRS buildFacturaRSComplete(Factura factura) {
        FacturaRS facturaRS = FacturaRSTransform.buildFacturaRS(factura);
        List<FacturaDetalleRS> detalles = new ArrayList<>();
        factura.getDetalles().forEach(d -> {
            detalles.add(FacturaRSTransform.buildDetalleFactura(d));
        });
        facturaRS.setDetalles(detalles);
        List<FacturaImpuestoRS> impuestos = new ArrayList<>();
        factura.getImpuestos().forEach((i) -> {
            impuestos.add(FacturaRSTransform.buildFacturaImpuesto(i));
        });
        facturaRS.setImpuestos(impuestos);
        return facturaRS;
    }

    public static FacturaRS buildFacturaRS(Factura factura) {
        return FacturaRS.builder()
                .fecha(factura.getFecha())
                .establecimiento(factura.getCodigoEstablecimiento())
                .numeroAutorizacion(factura.getNumeroAutorizacion())
                .puntoEmision(factura.getPuntoEmision())
                .secuencial(String.format("%09d", factura.getSecuencial()))
                .subtotal(factura.getSubtotal())
                .total(factura.getTotal())
                .tipoIdentificacion(factura.getCliente().getTipoIdentificacion())
                .identificacion(factura.getCliente().getIdentificacion())
                .nombre(("RUC".equals(factura.getCliente().getTipoIdentificacion())
                        ? factura.getCliente().getRazonSocial()
                        : factura.getCliente().getApellido() + " " + factura.getCliente().getNombre()))
                .build();
    }

    private static FacturaDetalleRS buildDetalleFactura(FacturaDetalle detalle) {
        return FacturaDetalleRS.builder()
                .cantidad(detalle.getCantidad())
                .precioUnitario(detalle.getPrecioUnitario())
                .producto(detalle.getNombreProducto())
                .subtotal(detalle.getSubtotal())
                .build();
    }

    private static FacturaImpuestoRS buildFacturaImpuesto(FacturaImpuesto impuesto) {
        return FacturaImpuestoRS.builder()
                .porcentaje(impuesto.getPk().getPorcentaje())
                .valor(impuesto.getValor())
                .siglas(impuesto.getImpuestoPorcentaje().getImpuesto().getSiglas())
                .build();
    }

}
