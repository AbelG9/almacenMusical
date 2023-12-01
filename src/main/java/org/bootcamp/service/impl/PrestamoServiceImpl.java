package org.bootcamp.service.impl;

import org.bootcamp.dao.PrestamoDao;
import org.bootcamp.model.Prestamo;
import org.bootcamp.service.PrestamoService;

import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    PrestamoDao prestamoDao;

    public PrestamoServiceImpl(PrestamoDao prestamoDao) {
        this.prestamoDao = prestamoDao;
    }

    @Override
    public void loanArt(int articuloID, int userID) {
        prestamoDao.loanArt(articuloID, userID);
    }

    @Override
    public void returnArt(int articuloID, int userID) {
        prestamoDao.returnArt(articuloID, userID);
    }

    @Override
    public List<Prestamo> getLoansByUserId(int userID) {
        return prestamoDao.getLoansByUserId(userID);
    }

    @Override
    public List<Prestamo> getLoansByArtId(int articuloID) {
        return prestamoDao.getLoansByArtId(articuloID);
    }

    @Override
    public List<Prestamo> getAllLoansByUserId(int userID) {
        return prestamoDao.getAllLoansByUserId(userID);
    }

    @Override
    public List<Prestamo> getAllLoansByArtId(int articuloID) {
        return prestamoDao.getAllLoansByArtId(articuloID);
    }
}
