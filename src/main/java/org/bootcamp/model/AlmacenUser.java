package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AlmacenUser {
    private int userID;
    private String userName;
    private List<AlmacenArticulo> loanedItems;

    public void showUserDetails(){
        System.out.println("--------------------------------------------");
        System.out.println("Usuario ID: " + userID);
        System.out.println("Nombre de usuario: " + userName);
        if(!loanedItems.isEmpty()){
            System.out.println("-----------------------------");
            System.out.println("Articulos prestados :");
            System.out.println("-----------------------------");
            int contador = 1;
            for (AlmacenArticulo articulo: loanedItems){
                System.out.println("Articulo " + contador + " :");
                articulo.showDetails();
                contador++;
            }
        } else {
            System.out.println("No tiene articulos prestados");
        }
        System.out.println("--------------------------------------------");
    }
}
