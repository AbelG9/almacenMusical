package org.bootcamp.dao.impl;

import org.bootcamp.dao.AlmacenArticuloDao;
import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.model.Instrumento;
import org.bootcamp.model.Partitura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<AlmacenArticulo> showAllArt() {
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
    public void loanArt(int articuloID, int userID) {
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

    @Override
    public void returnArt(int articuloID, int userID) {
        try{
            String sql = "update prestamos set estado = ? where usuario = ? and articulo = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, 0);
            psmt.setInt(2, userID);
            psmt.setInt(3, articuloID);
            psmt.setInt(4, 1);
            psmt.executeUpdate();

            String sql2 = "update articulos set isLoaned = ? where articulo_id = ?";
            PreparedStatement psmt2 = connection.prepareStatement(sql2);
            psmt2.setBoolean(1,false);
            psmt2.setInt(2, articuloID);
            psmt2.executeUpdate();

            System.out.println("Articulo devuelto exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public AlmacenArticulo findArtById(int articuloID) {
        try{
            String sql = "select * from articulos where articulo_id = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, articuloID);
            psmt.setInt(2, 1);
            ResultSet resultSet = psmt.executeQuery();
            AlmacenArticulo articulo;
            while(resultSet.next()){
                int id = resultSet.getInt("articulo_id");
                String nombre = resultSet.getString("nombre");
                boolean isLoaned = resultSet.getBoolean("isLoaned");
                String itemType = resultSet.getString("tipoArticulo");

                if(itemType.equals("Instrumento")){
                    String desDueño = resultSet.getString("desDuenio");

                    Instrumento instrumento = new Instrumento();
                    instrumento.setArticuloID(id);
                    instrumento.setNombreArticulo(nombre);
                    instrumento.setIsLoaned(isLoaned);
                    instrumento.setDesDueño(desDueño);
                    return instrumento;
                }
                else if (itemType.equals("Partitura")){
                    String autor = resultSet.getString("autor");
                    int duration = resultSet.getInt("duration");

                    Partitura partitura = new Partitura();
                    partitura.setArticuloID(id);
                    partitura.setNombreArticulo(nombre);
                    partitura.setIsLoaned(isLoaned);
                    partitura.setAutor(autor);
                    partitura.setDuration(duration);
                    return partitura;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
