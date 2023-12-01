package org.bootcamp.dao.impl;

import org.bootcamp.dao.UserDao;
import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.model.User;
import org.bootcamp.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection connection;  //conexion bd
    private Connection dbConnection = new DbConnection().getConnection();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User user) {
        try{
            String sql = "INSERT INTO usuarios (nombre, estado) VALUES (?, ?)";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1, user.getNombre());
            psmt.setInt(2, 1);
            psmt.executeUpdate();
            System.out.println("Usuario ingresado correctamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> showAllUsers() {
        try{
            String sql = "select * from usuarios where estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, 1);
            ResultSet resultSet = psmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("usuario_id");
                String nombre = resultSet.getString("nombre");

                List<AlmacenArticulo> articuloList = new ArrayList<>();

                User user = new User();
                user.setUserID(id);
                user.setNombre(nombre);
                user.setArticuloList(articuloList);
                users.add(user);
            }
            return users;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void editUser(User user) {
        try{
            String sql = "update usuarios set nombre = ? where usuario_id = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1,user.getNombre());
            psmt.setInt(2, user.getUserID());
            psmt.executeUpdate();

            System.out.println("Usuario editado exitosamente");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
    }

    @Override
    public User findUserById(int userID) {
        try{
            String sql = "select * from usuarios where usuario_id = ? and estado = ?";
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, userID);
            psmt.setInt(2, 1);
            ResultSet resultSet = psmt.executeQuery();
            User user = new User();
            while (resultSet.next()){
                int id = resultSet.getInt("usuario_id");
                String nombre = resultSet.getString("nombre");

                List<AlmacenArticulo> articuloList = new ArrayList<>();

                user.setUserID(id);
                user.setNombre(nombre);
                user.setArticuloList(articuloList);
            }
            return user;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
