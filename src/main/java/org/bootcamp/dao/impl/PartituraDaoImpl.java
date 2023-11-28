package org.bootcamp.dao.impl;

import org.bootcamp.dao.PartituraDao;
import org.bootcamp.model.Partitura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class PartituraDaoImpl implements PartituraDao {
    private Connection connection;  //conexion bd

    public PartituraDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addPartitura(Partitura partitura) {
        try{
            String sql = "insert into articulos (nombre, isLoaned, autor, duration, tipoArticulo, estado) values(?,?,?,?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            String nombre = partitura.getNombreArticulo();
            Boolean isLoaned = partitura.getIsLoaned();
            String autor = partitura.getAutor();
            int duration = partitura.getDuration();
            String tipoArticulo = "Partitura";
            int estado = 1;
            psmt.setString(1, nombre);
            psmt.setBoolean(2, isLoaned);
            psmt.setString(3, autor);
            psmt.setInt(4, duration);
            psmt.setString(5, tipoArticulo);
            psmt.setInt(6, estado);
            psmt.executeUpdate();
            System.out.println("Partitura ingresada correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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
