package org.bootcamp.service.impl;

import org.bootcamp.dao.AlmacenArticuloDao;
import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.service.AlmacenArticuloService;

import java.util.List;

public class AlmacenArticuloServiceImpl implements AlmacenArticuloService {
    AlmacenArticuloDao almacenArticuloDao;

    public AlmacenArticuloServiceImpl(AlmacenArticuloDao almacenArticuloDao) {
        this.almacenArticuloDao = almacenArticuloDao;
    }

    @Override
    public void addArt(AlmacenArticulo almacenArticulo) {
        almacenArticuloDao.addArt(almacenArticulo);
    }

    @Override
    public void updateArt(AlmacenArticulo almacenArticulo) {
        almacenArticuloDao.updateArt(almacenArticulo);
    }

    @Override
    public List<AlmacenArticulo> showAllArt() {
        return almacenArticuloDao.showAllArt();
    }

    @Override
    public void deleteArt(int id) {
        almacenArticuloDao.deleteArt(id);
    }

    @Override
    public AlmacenArticulo findArtById(int articuloID) {
        return almacenArticuloDao.findArtById(articuloID);
    }
}
