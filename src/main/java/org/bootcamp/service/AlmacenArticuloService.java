package org.bootcamp.service;

import org.bootcamp.model.AlmacenArticulo;

import java.util.List;

public interface AlmacenArticuloService {
    public void addArticulo(AlmacenArticulo almacenArticulo);

    public void updateArticulo(AlmacenArticulo almacenArticulo);

    public List<AlmacenArticulo> showAllArticulos();

    public void deleteArticulo(int id);

    public void loanArt(int articuloID, int userID);
    public void returnArt(int articuloID, int userID);
}
