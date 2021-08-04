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
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

/**
 * Representa las columnas que conforman la clave primaria de la tabla IMPUESTO_PORCENTAJE.
 *
 * @author Hendrix
 */
@Data
@Embeddable
public class ImpuestoPorcentajePK implements Serializable {

    @Column(name = "COD_IMPUESTO", nullable = false, length = 3)
    private String codigoImpuesto;

    @Column(name = "PORCENTAJE", nullable = false, precision = 4, scale = 1)
    private BigDecimal porcentaje;

}
