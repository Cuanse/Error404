/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tique
 */
public class Conexion {

    public Conexion() throws SQLException, ClassNotFoundException {
        getConexion();
    }
    ;
    private static Connection con;

    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://proyecto.cdpsawbnswbf.us-east-1.rds.amazonaws.com:3306/Proyecto";
        String user = "admin";
        String clave = "12345678";
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        con = DriverManager.getConnection(url, user, clave);
        return con;
    }

    public void setValues(String user, String password, String carrera) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO users values (?,?,?)");
        ps.setString(1, user);
        ps.setString(2, password);
        ps.setString(3, carrera);
        ps.executeUpdate();
        System.out.println("Agregado correctamente");
    }
    
    public void SearchUser(String Username, String Password) throws SQLException{
        ResultSet rs = con.createStatement().executeQuery("SELECT * from users");
        boolean encontrado = true;
        while(rs.next()){
            encontrado = false;
            if(rs.getString("UserName").equals(Username)){
                encontrado = true;
                if(rs.getString("Passwrd").equals(Password)){
                    System.out.println("Ingreso exitoso");
                }else{
                    System.out.println("Contraseña incorrecta");
                    break;
                }
            }
        }
        if(!encontrado){
            System.out.println("No tenemos registro de ese noombre de usuario");
        }
        
    }
}
