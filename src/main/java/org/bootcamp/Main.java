package org.bootcamp;

import org.bootcamp.utils.DbConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();


    }
}