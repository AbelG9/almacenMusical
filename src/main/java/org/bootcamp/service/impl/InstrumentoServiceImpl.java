package org.bootcamp.service.impl;

import org.bootcamp.dao.InstrumentoDao;
import org.bootcamp.model.Instrumento;
import org.bootcamp.service.InstrumentoService;

import java.util.List;

public class InstrumentoServiceImpl implements InstrumentoService {
    InstrumentoDao instrumentoDao;

    public InstrumentoServiceImpl(InstrumentoDao instrumentoDao) {
        this.instrumentoDao = instrumentoDao;
    }

    @Override
    public void addInstrumento(Instrumento instrumento) {
        instrumentoDao.addInstrumento(instrumento);
    }

    @Override
    public void updateInstrumento(Instrumento instrumento) {

    }

    @Override
    public List<Instrumento> showAllInstrumentos() {
        return null;
    }

    @Override
    public void deleteInstrumento(int id) {

    }
}
