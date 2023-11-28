package org.bootcamp.service.impl;

import org.bootcamp.dao.AlmacenArticuloDao;
import org.bootcamp.dao.impl.AlmacenArticuloDaoImpl;
import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.service.AlmacenArticuloService;

import java.util.List;

public class AlmacenArticuloServiceImpl implements AlmacenArticuloService {
    AlmacenArticuloDao almacenArticuloDao;

    public AlmacenArticuloServiceImpl(AlmacenArticuloDao almacenArticuloDao) {
        this.almacenArticuloDao = almacenArticuloDao;
    }

    @Override
    public void addArticulo(AlmacenArticulo almacenArticulo) {

    }

    @Override
    public void updateArticulo(AlmacenArticulo almacenArticulo) {

    }

    @Override
    public List<AlmacenArticulo> showAllArticulos() {
        return almacenArticuloDao.showAllArticulos();
    }

    @Override
    public void deleteArticulo(int id) {

    }
}
