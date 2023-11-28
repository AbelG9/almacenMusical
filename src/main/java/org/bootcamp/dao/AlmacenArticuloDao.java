package org.bootcamp.dao;

import org.bootcamp.model.AlmacenArticulo;

import java.util.List;

public interface AlmacenArticuloDao {
    public void addArticulo(AlmacenArticulo almacenArticulo);

    public void updateArticulo(AlmacenArticulo almacenArticulo);

    public List<AlmacenArticulo> showAllArticulos();

    public void deleteArticulo(int id);
}
