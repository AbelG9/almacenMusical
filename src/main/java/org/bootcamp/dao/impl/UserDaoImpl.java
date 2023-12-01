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
    }

    @Override
    public void deleteUser(int id) {
    }

    @Override
    public User findUserById(int userID) {
        return null;
    }
}
