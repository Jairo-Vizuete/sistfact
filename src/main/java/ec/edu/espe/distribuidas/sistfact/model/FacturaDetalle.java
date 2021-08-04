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
package ec.edu.espe.distribuidas.sistfact.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa a la tabla FACTURA_DETALLE.
 *
 * @author Hendrix
 */
@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle implements Serializable {

    @EmbeddedId
    private FacturaDetallePK pk;

    @Column(name = "CANTIDAD", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "NOMBRE_PRODUCTO", nullable = false, length = 100)
    private String nombreProducto;

    @Column(name = "PRECIO_UNITARIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "SUBTOTAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @MapsId("codigoFactura")
    @JoinColumn(name = "COD_FACTURA", referencedColumnName = "COD_FACTURA", nullable = false, insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    @JoinColumn(name = "COD_PRODUCTO", referencedColumnName = "COD_PRODUCTO", nullable = false, insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaDetalle")
    private List<FacturaDetalleImpuesto> detalleImpuestos;

    public FacturaDetalle() {
    }

    public FacturaDetalle(FacturaDetallePK pk) {
        this.pk = pk;
    }

    public FacturaDetalle(Integer codigoFactura, String codigoProducto) {
        this.pk = new FacturaDetallePK(codigoFactura, codigoProducto);
    }

    public FacturaDetallePK getPk() {
        return pk;
    }

    public void setPk(FacturaDetallePK pk) {
        this.pk = pk;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<FacturaDetalleImpuesto> getDetalleImpuestos() {
        return detalleImpuestos;
    }

    public void setDetalleImpuestos(List<FacturaDetalleImpuesto> detalleImpuestos) {
        this.detalleImpuestos = detalleImpuestos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FacturaDetalle other = (FacturaDetalle) obj;
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FacturaDetalle[ facturaDetallePK=" + pk + " ]";
    }

}
