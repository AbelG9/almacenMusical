package org.bootcamp.dao;

import org.bootcamp.model.Instrumento;
import org.bootcamp.model.Partitura;

import java.util.List;

public interface PartituraDao {
    public void addPartitura(Partitura partitura);

    public void updatePartitura(Partitura partitura);

    public List<Partitura> showAllPartituras();

    public void deletePartitura(int id);
}
