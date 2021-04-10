package com.productos.controllers.intfdao;

import com.productos.models.ModelProducto;

import java.util.List;

/**
 * Interfaz de productos (PATRON DAO)
 *
 * @autor JamesTovar
 * @email u20172163883@usco.edu.co
 */
public interface IProducto {

    boolean create(ModelProducto modelProducto);

    ModelProducto getById(int id);

    List<ModelProducto> getAll();

    List<ModelProducto> getBySearch(String buscar);

    boolean edit(ModelProducto modelProducto);

    boolean delete(int id);

}