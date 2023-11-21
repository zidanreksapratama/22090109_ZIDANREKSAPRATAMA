/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author LENOVO
 */
public class Koneksi {
    static Connection Con;
    public static Connection getConnection() throws SQLException {
        String db="jdbc:mysql://localhost:3306/dbcrew";
        String user = "root";
        String pass = "";
        
        if(Con==null) {
            Con = DriverManager.getConnection(db,user,pass);
        }
        return Con;
    }
}
