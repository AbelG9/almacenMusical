package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userID;
    private String nombre;
    private int estado;
    private List<AlmacenArticulo> articuloList;

    public void showUserDetails() {
        System.out.println("==========================");
        System.out.println("     Datos de Usuario");
        System.out.println("==========================");
        System.out.println("UserID:" + userID);
        System.out.println("Nombre:" + nombre);
        System.out.println("- - - - - - - - - - - - -");
        if(!this.articuloList.isEmpty()){
            System.out.println("Tiene " + this.articuloList.size() + " articulos prestados :");
            System.out.println("--------------------");
            int contador = 1;
            for (AlmacenArticulo articulo: this.articuloList){
                System.out.println("Articulo " + contador + " :");
                articulo.showDetails();
                contador++;
            }
        } else {
            System.out.println("No tiene articulos prestados");
        }
    }
}
