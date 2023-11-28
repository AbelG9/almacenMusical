package org.bootcamp.service.impl;

import org.bootcamp.dao.PartituraDao;
import org.bootcamp.model.Partitura;
import org.bootcamp.service.PartituraService;

import java.util.List;

public class PartituraServiceImpl implements PartituraService {
    PartituraDao partituraDao;

    public PartituraServiceImpl(PartituraDao partituraDao) {
        this.partituraDao = partituraDao;
    }


    @Override
    public void addPartitura(Partitura partitura) {
        partituraDao.addPartitura(partitura);
    }

    @Override
    public void updatePartitura(Partitura partitura) {

    }

    @Override
    public List<Partitura> showAllPartituras() {
        return null;
    }

    @Override
    public void deletePartitura(int id) {

    }
}
