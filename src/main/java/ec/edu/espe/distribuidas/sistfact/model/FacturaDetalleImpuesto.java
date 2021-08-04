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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa a la tabla: FACTURA_DETALLE_IMPUESTO.
 *
 * @author Hendrix
 */
@Entity
@Table(name = "factura_detalle_impuesto")
public class FacturaDetalleImpuesto implements Serializable {

    @EmbeddedId
    private FacturaDetalleImpuestoPK pk;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @JoinColumns({
        @JoinColumn(name = "COD_FACTURA", referencedColumnName = "COD_FACTURA", nullable = false, insertable = false, 
                updatable = false),
        @JoinColumn(name = "COD_PRODUCTO", referencedColumnName = "COD_PRODUCTO", nullable = false, insertable = false, 
                updatable = false)})
    @ManyToOne(optional = false)
    private FacturaDetalle facturaDetalle;

    public FacturaDetalleImpuesto() {
    }

    public FacturaDetalleImpuesto(FacturaDetalleImpuestoPK pk) {
        this.pk = pk;
    }

    public FacturaDetalleImpuesto(FacturaDetalleImpuestoPK facturaDetalleImpuestoPK, BigDecimal valor) {
        this.pk = facturaDetalleImpuestoPK;
        this.valor = valor;
    }

    public FacturaDetalleImpuesto(String codImpuesto, BigDecimal porcentaje, Integer codFactura, String codProducto) {
        this.pk = new FacturaDetalleImpuestoPK(codImpuesto, porcentaje, codFactura, codProducto);
    }

    public FacturaDetalleImpuestoPK getPk() {
        return pk;
    }

    public void setPk(FacturaDetalleImpuestoPK pk) {
        this.pk = pk;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public FacturaDetalle getFacturaDetalle() {
        return facturaDetalle;
    }

    public void setFacturaDetalle(FacturaDetalle facturaDetalle) {
        this.facturaDetalle = facturaDetalle;
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
        final FacturaDetalleImpuesto other = (FacturaDetalleImpuesto) obj;
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FacturaDetalleImpuesto[ pk=" + pk + " ]";
    }

}
