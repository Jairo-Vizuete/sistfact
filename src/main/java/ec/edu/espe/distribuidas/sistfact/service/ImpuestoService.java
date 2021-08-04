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
package ec.edu.espe.distribuidas.sistfact.service;

import ec.edu.espe.distribuidas.sistfact.dao.ImpuestoPorcentajeRepository;
import ec.edu.espe.distribuidas.sistfact.dao.ImpuestoRepository;
import ec.edu.espe.distribuidas.sistfact.exception.CreateException;
import ec.edu.espe.distribuidas.sistfact.model.Impuesto;
import ec.edu.espe.distribuidas.sistfact.model.ImpuestoPorcentaje;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hendrix
 */
@Service
public class ImpuestoService {

    private final ImpuestoRepository impuestoRepo;
    private final ImpuestoPorcentajeRepository impuestoPorcentajeRepo;

    public ImpuestoService(ImpuestoRepository impuestoRepo, ImpuestoPorcentajeRepository impuestoPorcentajeRepo) {
        this.impuestoRepo = impuestoRepo;
        this.impuestoPorcentajeRepo = impuestoPorcentajeRepo;
    }

    @Transactional
    public void createImpuesto(Impuesto impuesto) {
        Optional<Impuesto> impuestoOpt = this.obtenerPorCodigo(impuesto.getCodigo());
        if (!impuestoOpt.isPresent()) {
            this.impuestoRepo.save(impuesto);
        } else {
            throw new CreateException("El impuesto con codigo: " + impuesto.getCodigo() + " ya existe");
        }
    }

    @Transactional
    public void modifyImpuesto(Impuesto impuesto) {
        this.impuestoRepo.save(impuesto);
    }

    public List<Impuesto> listAll() {
        return this.impuestoRepo.findAll();
    }

    public Optional<Impuesto> obtenerPorCodigo(String codigo) {
        return this.impuestoRepo.findById(codigo);
    }

    @Transactional
    public void createImpuestoPorcentaje(ImpuestoPorcentaje impuestoPorcentaje) {
        impuestoPorcentaje.setEstado("INA");
        impuestoPorcentaje.setFechaInicio(new Date());
        impuestoPorcentaje.setFechaFin(null);
        this.impuestoPorcentajeRepo.save(impuestoPorcentaje);
    }

    @Transactional
    public void updateImpuestoPorcentaje(ImpuestoPorcentaje impuestoPorcentaje) {
        if ("INA".equals(impuestoPorcentaje.getEstado()) && impuestoPorcentaje.getFechaFin() != null 
                && impuestoPorcentaje.getFechaFin().before(new Date())) {
            this.impuestoPorcentajeRepo.save(impuestoPorcentaje);
        } else {
            //lanzar exception
        }
    }

    public Impuesto listarPorcentajesDeImpuesto(String codigo) {
        Optional<Impuesto> impuestoOpt = this.impuestoRepo.findById(codigo);
        if (impuestoOpt.isPresent()) {
            Impuesto impuesto = impuestoOpt.get();
            
            impuesto.getImpuestoPorcentajes();
            return impuesto;
        } else {
            return null;
        }
    }
}
