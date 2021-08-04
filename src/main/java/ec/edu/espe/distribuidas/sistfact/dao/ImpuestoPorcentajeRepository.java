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
package ec.edu.espe.distribuidas.sistfact.dao;

import ec.edu.espe.distribuidas.sistfact.model.ImpuestoPorcentaje;
import ec.edu.espe.distribuidas.sistfact.model.ImpuestoPorcentajePK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hendrix
 */
public interface ImpuestoPorcentajeRepository extends JpaRepository<ImpuestoPorcentaje, ImpuestoPorcentajePK> {

    List<ImpuestoPorcentaje> findByPkCodigoImpuestoAndEstadoOrderByPkPorcentaje(String codigoImpuesto, String estado);
}
