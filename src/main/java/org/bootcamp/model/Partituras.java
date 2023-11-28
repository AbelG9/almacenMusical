package org.bootcamp.model;

import lombok.Data;

public class Partituras extends AlmacenArticulo{
    private String autor;
    private int duration;

    public Partituras(int articuloID, String nombreArticulo, Boolean isLoaned, String autor, int duration) {
        super(articuloID, nombreArticulo, isLoaned);
        this.autor = autor;
        this.duration = duration;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void showDetails() {
        System.out.println("--------------------------------------------");
        System.out.println("ArticuloID: " + articuloID);
        System.out.println("Nombre del articulo: " + nombreArticulo);
        System.out.println("Esta prestado: " + isLoaned);
        System.out.println("Autor: " + autor);
        System.out.println("Duracion: " + duration);
        System.out.println("--------------------------------------------");
    }
}
