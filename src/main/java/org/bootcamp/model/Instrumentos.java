package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Instrumentos extends AlmacenArticulo{

    private String desDueño;

    public Instrumentos(int articuloID, String nombreArticulo, Boolean isLoaned, String desDueño) {
        super(articuloID, nombreArticulo, isLoaned);
        this.desDueño = desDueño;
    }

    public String getDesDueño() {
        return desDueño;
    }

    public void setDesDueño(String desDueño) {
        this.desDueño = desDueño;
    }

    @Override
    public void showDetails(){
        System.out.println("--------------------------------------------");
        System.out.println("ArticuloID: " + articuloID);
        System.out.println("Nombre del articulo: " + nombreArticulo);
        System.out.println("Esta prestado: " + isLoaned);
        System.out.println("Dueño: " + desDueño);
        System.out.println("--------------------------------------------");
    }
}
