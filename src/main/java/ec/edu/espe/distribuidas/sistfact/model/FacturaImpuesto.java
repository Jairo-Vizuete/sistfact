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
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Representa a la tabla: FACTURA_IMPUESTO.
 *
 * @author Hendrix
 */
@Entity
@Table(name = "factura_impuesto")
public class FacturaImpuesto implements Serializable {

    @EmbeddedId
    private FacturaImpuestoPK pk;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @MapsId("codigoFactura")
    @JoinColumn(name = "COD_FACTURA", referencedColumnName = "COD_FACTURA", nullable = false, insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    @JoinColumns({
        @JoinColumn(name = "COD_IMPUESTO", referencedColumnName = "COD_IMPUESTO", nullable = false, insertable = false,
                updatable = false),
        @JoinColumn(name = "PORCENTAJE", referencedColumnName = "PORCENTAJE", nullable = false, insertable = false, 
                updatable = false)})
    @ManyToOne(optional = false)
    private ImpuestoPorcentaje impuestoPorcentaje;

    public FacturaImpuesto() {
    }

    public FacturaImpuesto(FacturaImpuestoPK pk) {
        this.pk = pk;
    }

    public FacturaImpuesto(Integer codigoFactura, String codigoImpuesto, BigDecimal porcentaje){
        FacturaImpuestoPK facturaImpuestoPK = new FacturaImpuestoPK();
        facturaImpuestoPK.setCodigoFactura(codigoFactura);
        facturaImpuestoPK.setCodigoImpuesto(codigoImpuesto);
        facturaImpuestoPK.setPorcentaje(porcentaje);
        this.pk = facturaImpuestoPK;
    }
    
//    public FacturaImpuesto(Integer codigoFactura, String codigoImpuesto, BigDecimal porcentaje) {
//        this.pk = new FacturaImpuestoPK(codigoFactura, codigoImpuesto, porcentaje);
//    }

    public FacturaImpuestoPK getPk() {
        return pk;
    }

    public void setPk(FacturaImpuestoPK pk) {
        this.pk = pk;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public ImpuestoPorcentaje getImpuestoPorcentaje() {
        return impuestoPorcentaje;
    }

    public void setImpuestoPorcentaje(ImpuestoPorcentaje impuestoPorcentaje) {
        this.impuestoPorcentaje = impuestoPorcentaje;
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
        final FacturaImpuesto other = (FacturaImpuesto) obj;
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FacturaImpuesto[ pk=" + pk + " ]";
    }

}
