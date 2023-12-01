package org.bootcamp.dao;

import org.bootcamp.model.AlmacenArticulo;

import java.util.List;

public interface AlmacenArticuloDao {
    public void addArticulo(AlmacenArticulo almacenArticulo);

    public void updateArticulo(AlmacenArticulo almacenArticulo);

    public List<AlmacenArticulo> showAllArt();

    public void deleteArticulo(int id);

    public void loanArt(int articuloID, int userID);

    public void returnArt(int articuloID, int userID);

    public AlmacenArticulo findArtById(int articuloID);
}
