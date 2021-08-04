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
 * Representa a la clave primaria de la tabla: FACTURA_IMPUESTO.
 *
 * @author Hendrix
 */
@Embeddable
public class FacturaImpuestoPK implements Serializable {

    @Column(name = "COD_FACTURA", nullable = false)
    private Integer codigoFactura;

    @Column(name = "COD_IMPUESTO", nullable = false, length = 3)
    private String codigoImpuesto;

    @Column(name = "PORCENTAJE", nullable = false, precision = 4, scale = 1)
    private BigDecimal porcentaje;

    public FacturaImpuestoPK() {
    }

    public FacturaImpuestoPK(Integer codigoFactura, String codigoImpuesto, BigDecimal porcentaje) {
        this.codigoFactura = codigoFactura;
        this.codigoImpuesto = codigoImpuesto;
        this.porcentaje = porcentaje;
    }

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoFactura;
        hash += (codigoImpuesto != null ? codigoImpuesto.hashCode() : 0);
        hash += (porcentaje != null ? porcentaje.hashCode() : 0);
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
        final FacturaImpuestoPK other = (FacturaImpuestoPK) obj;
        if (!Objects.equals(this.codigoImpuesto, other.codigoImpuesto)) {
            return false;
        }
        if (!Objects.equals(this.codigoFactura, other.codigoFactura)) {
            return false;
        }
        if (!Objects.equals(this.porcentaje, other.porcentaje)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ".FacturaImpuestoPK[ codigoFactura=" + codigoFactura + ", codigoImpuesto=" + codigoImpuesto
                + ", porcentaje=" + porcentaje + " ]";
    }

}
