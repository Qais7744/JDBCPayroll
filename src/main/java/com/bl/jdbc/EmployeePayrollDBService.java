package com.bl.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            String sql = "SELECT * FROM employee_payroll_data;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
            while (result.next()) {
                count++;
                int id = result.getInt("id");
                String name = result.getString("name");
                double salary = result.getDouble("salary");
                LocalDate start = result.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, start));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    public Connection getConnection() {

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "Altamash@93";
        Connection con = null;
        System.out.println("Connection to database" + jdbcURL);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath", e);
        }

        try {
            con = DriverManager.getConnection(jdbcURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection is successful" + con);
        return con;
    }

    public static void main(String args[]) throws SQLException {
        EmployeePayrollDBService s = new EmployeePayrollDBService();
        s.readData();
        System.out.println(s.readData());
    }
}
