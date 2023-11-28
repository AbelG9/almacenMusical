package org.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AlmacenArticulo {
    protected int articuloID;
    protected String nombreArticulo;
    protected Boolean isLoaned;

    public abstract void showDetails();
}
