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

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a la tabla IMPUESTO_PORCENTAJE.
 *
 * @author Hendrix
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "impuesto_porcentaje")
public class ImpuestoPorcentaje implements Serializable {

    @EmbeddedId
    private ImpuestoPorcentajePK pk;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "COD_IMPUESTO", referencedColumnName = "COD_IMPUESTO", nullable = false, insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private Impuesto impuesto;

    public ImpuestoPorcentaje(String codigoImpuesto, BigDecimal porcentaje) {
        ImpuestoPorcentajePK pkT = new ImpuestoPorcentajePK();
        pkT.setCodigoImpuesto(codigoImpuesto);
        pkT.setPorcentaje(porcentaje);
        this.pk = pkT;
    }

}
