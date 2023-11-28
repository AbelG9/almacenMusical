package org.bootcamp.dao.impl;

import org.bootcamp.dao.AlmacenArticuloDao;
import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.model.Instrumento;
import org.bootcamp.model.Partitura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlmacenArticuloDaoImpl implements AlmacenArticuloDao {
    private Connection connection;  //conexion bd

    public AlmacenArticuloDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addArticulo(AlmacenArticulo almacenArticulo) {

    }

    @Override
    public void updateArticulo(AlmacenArticulo almacenArticulo) {

    }

    @Override
    public List<AlmacenArticulo> showAllArticulos() {
        try{
            String sql = "select * from articulos";
            PreparedStatement psmt = connection.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();
            List<AlmacenArticulo> articulos = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("articulo_id");
                String nombre = resultSet.getString("nombre");
                Boolean isLoaned = resultSet.getBoolean("isLoaned");
                String desDueño = resultSet.getString("desDuenio");
                String autor = resultSet.getString("autor");
                int duration = resultSet.getInt("duration");
                String tipoArticulo = resultSet.getString("tipoArticulo");
                switch (tipoArticulo){
                    case "Instrumento":
                        Instrumento instrumento = new Instrumento();
                        instrumento.setArticuloID(id);
                        instrumento.setNombreArticulo(nombre);
                        instrumento.setIsLoaned(isLoaned);
                        instrumento.setDesDueño(desDueño);
                        articulos.add(instrumento);
                        break;
                    case "Partitura":
                        Partitura partitura = new Partitura();
                        partitura.setArticuloID(id);
                        partitura.setNombreArticulo(nombre);
                        partitura.setIsLoaned(isLoaned);
                        partitura.setAutor(autor);
                        partitura.setDuration(duration);
                        articulos.add(partitura);
                        break;
                }
            }
            return articulos;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteArticulo(int id) {

    }

    @Override
    public void loanArticulo(int articuloID, int userID) {
        try{
            String sql = "insert into prestamos (usuario, articulo, estado) values (?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, userID);
            psmt.setInt(2, articuloID);
            psmt.setInt(3, 1);
            psmt.executeUpdate();

            String sql2 = "update articulos set isLoaned = ? where articulo_id = ?";
            PreparedStatement psmt2 = connection.prepareStatement(sql2);
            psmt2.setBoolean(1,true);
            psmt2.setInt(2, articuloID);
            psmt2.executeUpdate();

            System.out.println("Articulo prestado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
