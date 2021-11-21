package com.bl.jdbc;

import java.sql.*;
import java.util.Enumeration;

public class Database {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "Altamash@93";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Exception: "+e.getMessage());
            throw new IllegalStateException("Cannot find the driver in the classpath", e);
        }
        listDriver();
        try {
            System.out.println("Connection to database: " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connection is successful !!!!!!!!" +connection);
        } catch (Exception e) {
            System.out.println("SQLException: "+e.getMessage());
        }
        try {
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    private static void listDriver() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass = driverList.nextElement();
            System.out.println(" "+ driverClass.getClass().getName());
        }
    }
}
