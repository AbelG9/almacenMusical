package org.bootcamp.dao;

import org.bootcamp.model.AlmacenArticulo;

import java.util.List;

public interface AlmacenArticuloDao {
    public void addArt(AlmacenArticulo almacenArticulo);

    public void updateArt(AlmacenArticulo almacenArticulo);

    public List<AlmacenArticulo> showAllArt();

    public void deleteArt(int id);

    public AlmacenArticulo findArtById(int articuloID);
}
