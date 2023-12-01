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
import java.util.Scanner;

public class AlmacenArticuloDaoImpl implements AlmacenArticuloDao {
    private Connection connection;  //conexion bd

    public AlmacenArticuloDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addArt(AlmacenArticulo almacenArticulo) {
        try{
            String sql = "insert into articulos (nombre, isLoaned, desDuenio, autor, duration, tipoArticulo, estado) values(?,?,?,?,?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1, almacenArticulo.getNombreArticulo());
            psmt.setBoolean(2, false);

            String tipoArticulo = almacenArticulo.getTipoArticulo();
            if (tipoArticulo.equals("Instrumento")) {
                Instrumento instrumento = (Instrumento) almacenArticulo;
                psmt.setString(3, instrumento.getDesDueño());
                psmt.setString(4, null);
                psmt.setInt(5, 0);
            } else if (tipoArticulo.equals("Partitura")){
                Partitura partitura = (Partitura) almacenArticulo;
                psmt.setString(3, null);
                psmt.setString(4, partitura.getAutor());
                psmt.setInt(5, partitura.getDuration());
            }
            psmt.setString(6, tipoArticulo);
            psmt.setInt(7, 1);
            psmt.executeUpdate();
            System.out.println(tipoArticulo + " ingresado correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateArt(AlmacenArticulo almacenArticulo) {
        try{
            String sql = "update articulos set nombre = ?, desDuenio = ?, autor = ?, duration = ? where articulo_id = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1, almacenArticulo.getNombreArticulo());

            String tipoArticulo = almacenArticulo.getTipoArticulo();
            if (tipoArticulo.equals("Instrumento")) {
                Instrumento instrumento = (Instrumento) almacenArticulo;
                psmt.setString(2, instrumento.getDesDueño());
                psmt.setString(3, null);
                psmt.setInt(4, 0);
            } else if (tipoArticulo.equals("Partitura")){
                Partitura partitura = (Partitura) almacenArticulo;
                psmt.setString(2, null);
                psmt.setString(3, partitura.getAutor());
                psmt.setInt(4, partitura.getDuration());
            }
            psmt.setInt(5, almacenArticulo.getArticuloID());
            psmt.executeUpdate();

            System.out.println("Articulo editado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<AlmacenArticulo> showAllArt() {
        try{
            String sql = "select * from articulos where estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, 1);
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
                        instrumento.setTipoArticulo(tipoArticulo);
                        articulos.add(instrumento);
                        break;
                    case "Partitura":
                        Partitura partitura = new Partitura();
                        partitura.setArticuloID(id);
                        partitura.setNombreArticulo(nombre);
                        partitura.setIsLoaned(isLoaned);
                        partitura.setAutor(autor);
                        partitura.setDuration(duration);
                        partitura.setTipoArticulo(tipoArticulo);
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
    public void deleteArt(int id) {
        System.out.println("Esta seguro de eliminar este articulo? (1: Si, 2: No)");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        if(option != 1) return;
        try{
            String sql = "update articulos set estado = ? where articulo_id = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, 0);
            psmt.setInt(2, id);
            psmt.executeUpdate();

            System.out.println("Articulo eliminado exitosamente");
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
                    instrumento.setTipoArticulo(itemType);
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
                    partitura.setTipoArticulo(itemType);
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
