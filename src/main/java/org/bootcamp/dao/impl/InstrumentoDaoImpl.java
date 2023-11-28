package org.bootcamp.dao.impl;

import org.bootcamp.dao.InstrumentoDao;
import org.bootcamp.model.Instrumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class InstrumentoDaoImpl implements InstrumentoDao {
    private Connection connection;  //conexion bd

    public InstrumentoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addInstrumento(Instrumento instrumento) {
        try{
            String sql = "insert into articulos (nombre, isLoaned, desDuenio, tipoArticulo, estado) values(?,?,?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            String nombre = instrumento.getNombreArticulo();
            Boolean isLoaned = instrumento.getIsLoaned();
            String desDuenio = instrumento.getDesDueño();
            String tipoArticulo = "Instrumento";
            int estado = 1;
            psmt.setString(1, nombre);
            psmt.setBoolean(2, isLoaned);
            psmt.setString(3, desDuenio);
            psmt.setString(4, tipoArticulo);
            psmt.setInt(5, estado);
            psmt.executeUpdate();
            System.out.println("Instrumento ingresado correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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
