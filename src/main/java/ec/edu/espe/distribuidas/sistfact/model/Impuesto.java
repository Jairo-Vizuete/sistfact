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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * Representa a la tabla IMPUESTO.
 *
 * @author Hendrix
 */
@Data
@Entity
@Table(name = "impuesto")
public class Impuesto implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Id
    @Column(name = "COD_IMPUESTO", nullable = false, length = 3)
    private String codigo;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "SIGLAS", nullable = false, length = 10)
    private String siglas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "impuesto", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ImpuestoPorcentaje> impuestoPorcentajes;

}
