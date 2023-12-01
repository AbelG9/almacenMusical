package org.bootcamp.service;

import org.bootcamp.model.AlmacenArticulo;

import java.util.List;

public interface AlmacenArticuloService {
    public void addArt(AlmacenArticulo almacenArticulo);

    public void updateArt(AlmacenArticulo almacenArticulo);

    public List<AlmacenArticulo> showAllArt();

    public void deleteArt(int id);

    public AlmacenArticulo findArtById(int articuloID);
}
