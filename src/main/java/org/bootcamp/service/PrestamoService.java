package org.bootcamp.service;

import org.bootcamp.model.Prestamo;

import java.util.List;

public interface PrestamoService {
    public void loanArt(int articuloID, int userID);
    public void returnArt(int articuloID, int userID);
    public List<Prestamo> getLoansByUserId(int userID);
    public List<Prestamo> getLoansByArtId(int articuloID);
    public List<Prestamo> getAllLoansByUserId(int userID);
    public List<Prestamo> getAllLoansByArtId(int articuloID);
}
