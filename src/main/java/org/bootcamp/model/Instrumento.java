package org.bootcamp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Instrumento extends AlmacenArticulo{

    private String desDueño;

    public Instrumento(int articuloID, String nombreArticulo, Boolean isLoaned, String tipoArticulo, String desDueño) {
        super(articuloID, nombreArticulo, isLoaned, tipoArticulo);
        this.desDueño = desDueño;
    }

    @Override
    public void showDetails(){
        System.out.println("--------------------------------------------");
        System.out.println("Instrumento");
        System.out.println("--------------------------------------------");
        System.out.println("ArticuloID: " + articuloID);
        System.out.println("Nombre del articulo: " + nombreArticulo);
        System.out.println("Esta prestado: " + isLoaned);
        System.out.println("Dueño: " + desDueño);
        System.out.println("--------------------------------------------");
    }
}
