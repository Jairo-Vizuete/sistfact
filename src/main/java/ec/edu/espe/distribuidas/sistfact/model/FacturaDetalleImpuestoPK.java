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
import javax.persistence.Embeddable;

/**
 * Representa a la clave primaria de la tabla: FACTURA_DETALLE_IMPUESTO.
 *
 * @author Hendrix
 */
@Embeddable
public class FacturaDetalleImpuestoPK implements Serializable {

    @Column(name = "COD_IMPUESTO", nullable = false, length = 3)
    private String codigoImpuesto;

    @Column(name = "PORCENTAJE", nullable = false, precision = 4, scale = 1)
    private BigDecimal porcentaje;

    @Column(name = "COD_FACTURA", nullable = false)
    private Integer codigoFactura;

    @Column(name = "COD_PRODUCTO", nullable = false, length = 20)
    private String codigoProducto;

    public FacturaDetalleImpuestoPK() {
    }

    public FacturaDetalleImpuestoPK(String codigoImpuesto, BigDecimal porcentaje, Integer codigoFactura,
            String codigoProducto) {
        this.codigoImpuesto = codigoImpuesto;
        this.porcentaje = porcentaje;
        this.codigoFactura = codigoFactura;
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(String codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoImpuesto != null ? codigoImpuesto.hashCode() : 0);
        hash += (porcentaje != null ? porcentaje.hashCode() : 0);
        hash += (int) codigoFactura;
        hash += (codigoProducto != null ? codigoProducto.hashCode() : 0);
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
        final FacturaDetalleImpuestoPK other = (FacturaDetalleImpuestoPK) obj;
        if (!Objects.equals(this.codigoImpuesto, other.codigoImpuesto)) {
            return false;
        }
        if (!Objects.equals(this.codigoProducto, other.codigoProducto)) {
            return false;
        }
        if (!Objects.equals(this.porcentaje, other.porcentaje)) {
            return false;
        }
        if (!Objects.equals(this.codigoFactura, other.codigoFactura)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FacturaDetalleImpuestoPK[ codigoImpuesto=" + codigoImpuesto + ", porcentaje=" + porcentaje
                + ", codigoFactura=" + codigoFactura + ", codigoProducto=" + codigoProducto + " ]";
    }

}
