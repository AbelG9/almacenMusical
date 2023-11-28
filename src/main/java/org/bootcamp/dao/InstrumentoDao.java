package org.bootcamp.dao;

import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.model.Instrumento;

import java.util.List;

public interface InstrumentoDao {
    public void addInstrumento(Instrumento instrumento);

    public void updateInstrumento(Instrumento instrumento);

    public List<Instrumento> showAllInstrumentos();

    public void deleteInstrumento(int id);
}
