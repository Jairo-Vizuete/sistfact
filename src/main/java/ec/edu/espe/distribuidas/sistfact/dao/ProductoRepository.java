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

import ec.edu.espe.distribuidas.sistfact.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hendrix
 */
public interface ProductoRepository extends JpaRepository<Producto, String> {

    List<Producto> findByEstadoOrderByNombre(String estado);

    List<Producto> findByNombreLikeOrderByNombre(String nombre);

    List<Producto> findByNombreLikeAndExistenciaGreaterThanOrderByNombre(String nombre, BigDecimal valor);
}
