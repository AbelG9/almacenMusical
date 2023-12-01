package org.bootcamp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Partitura extends AlmacenArticulo{
    private String autor;
    private int duration;

    public Partitura(int articuloID, String nombreArticulo, Boolean isLoaned, String tipoArticulo, String autor, int duration) {
        super(articuloID, nombreArticulo, isLoaned, tipoArticulo);
        this.autor = autor;
        this.duration = duration;
    }

    @Override
    public void showDetails() {
        System.out.println("--------------------------------------------");
        System.out.println("Partitura");
        System.out.println("--------------------------------------------");
        System.out.println("ArticuloID: " + articuloID);
        System.out.println("Nombre del articulo: " + nombreArticulo);
        System.out.println("Esta prestado: " + isLoaned);
        System.out.println("Autor: " + autor);
        System.out.println("Duracion: " + duration);
        System.out.println("--------------------------------------------");
    }
}
