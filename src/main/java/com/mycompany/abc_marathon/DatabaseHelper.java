/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.abc_marathon;

/**
 *
 * @author Muzamil-M
 */
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class DatabaseHelper {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/my";
        String user = "root";
        String password = "12345";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
//
//    public static DefaultTableModel getTableModel() {
//        DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Name", "Time"}, 0);
//        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
//            String query = "SELECT no, name, time FROM marathon";
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                String column1 = rs.getString("no");
//                String column2 = rs.getString("name");
//                String column3 = rs.getString("time");                   
//                model.addRow(new Object[]{column1, column2, column3});
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return model;
//    }
    public static Vector getDataAsListOfStringArrays() throws Exception {
        Vector<Vector<Object>> data = new Vector<>();

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String query = "SELECT no, name, time FROM marathon";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
           Vector<Object> row = new Vector<>();
                row.add(rs.getString("no"));
                row.add(rs.getString("name"));
                row.add(rs.getString("time"));
                data.add(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
        public static Boolean  insert(String name,String time){
         try (Connection conn = getConnection()) {
        String query = "INSERT INTO marathon (name, time) VALUES (?, ?)";
           PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setString(2,time);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        return false;
        }
        return true;
    }
        public static boolean truncate(){
             try (Connection conn = getConnection()) {
        String query = "TRUNCATE TABLE marathon";
                   PreparedStatement stmt = conn.prepareStatement(query);
            stmt.execute();

                 System.out.println(query);
        return true;
        } catch (Exception e) {
            e.printStackTrace();
        return false;
        }
        }
}
