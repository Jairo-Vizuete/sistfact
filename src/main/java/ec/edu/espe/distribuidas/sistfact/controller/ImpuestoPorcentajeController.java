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
package ec.edu.espe.distribuidas.sistfact.controller;

import ec.edu.espe.distribuidas.sistfact.dto.ImpuestoPorcentajeRQ;
import ec.edu.espe.distribuidas.sistfact.dto.ImpuestoPorcentajeRS;
import ec.edu.espe.distribuidas.sistfact.model.Impuesto;
import ec.edu.espe.distribuidas.sistfact.model.ImpuestoPorcentaje;
import ec.edu.espe.distribuidas.sistfact.service.ImpuestoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendrix
 */

@RestController
@RequestMapping("/api/impuesto/porcentaje")
public class ImpuestoPorcentajeController {

    private final ImpuestoService service;

    public ImpuestoPorcentajeController(ImpuestoService service) {
        this.service = service;
    }

    @GetMapping(value = "{codigo}")
    public ResponseEntity obtenerPorcentajesImpuesto(@PathVariable("codigo") String codigo) {
        try {
            Impuesto impuesto = this.service.listarPorcentajesDeImpuesto(codigo);
            List<ImpuestoPorcentajeRS> response = new ArrayList<>();
            for (ImpuestoPorcentaje ip : impuesto.getImpuestoPorcentajes()) {
                response.add(ImpuestoPorcentajeRS.builder()
                        .codigoImpuesto(ip.getPk().getCodigoImpuesto())
                        .porcentaje(ip.getPk().getPorcentaje())
                        .estado(ip.getEstado())
                        .fechaInicio(ip.getFechaInicio())
                        .fechaFin(ip.getFechaFin()).build());

            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity crear(@RequestBody ImpuestoPorcentajeRQ request) {
        ImpuestoPorcentaje ip = new ImpuestoPorcentaje(request.getCodigoImpuesto(), request.getPorcentaje());
        this.service.createImpuestoPorcentaje(ip);
        return ResponseEntity.ok().build();
    }
}
