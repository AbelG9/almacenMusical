package org.bootcamp.service;

import org.bootcamp.model.Instrumento;

import java.util.List;

public interface InstrumentoService {
    public void addInstrumento(Instrumento instrumento);

    public void updateInstrumento(Instrumento instrumento);

    public List<Instrumento> showAllInstrumentos();

    public void deleteInstrumento(int id);
}
