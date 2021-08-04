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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * Representa a la tabla FACTURA.
 *
 * @author Hendrix
 */
@Data
@Entity
@Table(name = "factura")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_FACTURA", nullable = false)
    private Integer codigo;

    @Column(name = "COD_CLIENTE", nullable = false)
    private Integer codigoCliente;

    @Column(name = "COD_ESTABLECIMIENTO", nullable = false, length = 3)
    private String codigoEstablecimiento;

    @Column(name = "PUNTO_EMISION", nullable = false, length = 3)
    private String puntoEmision;

    @Column(name = "SECUENCIAL", nullable = false)
    private Integer secuencial;

    @Column(name = "NUMERO_AUTORIZACION", length = 40)
    private String numeroAutorizacion;

    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "SUBTOTAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Basic(optional = false)
    @Column(name = "TOTAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    //@MapsId
    private List<FacturaDetalle> detalles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private List<FacturaImpuesto> impuestos;

    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE", nullable = false, insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

}
