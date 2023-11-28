package org.bootcamp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Almacen {
    private List<AlmacenArticulo> almacenArticuloList;

    public Almacen(){
        this.almacenArticuloList = new ArrayList<>();
    }

    public void addArt(AlmacenArticulo almacenArticulo){

    }

    public List<AlmacenArticulo> showAllArt(){
        return null;
    }

    public void loanArt(int articuloID, int userID){

    }
    public void returnArt(int articuloID, int userID){

    }

    public void showAllUsers(){

    }


}
