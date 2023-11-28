package org.bootcamp.service;

import org.bootcamp.model.Partitura;

import java.util.List;

public interface PartituraService {
    public void addPartitura(Partitura partitura);

    public void updatePartitura(Partitura partitura);

    public List<Partitura> showAllPartituras();

    public void deletePartitura(int id);
}