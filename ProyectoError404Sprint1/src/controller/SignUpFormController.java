/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Alerta;
import Modelo.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class SignUpFormController implements Initializable {
    Alerta anuncio = new Alerta();
    private Connection bd;
    @FXML
    private VBox panelFormSignUp;
    @FXML
    private TextField txtUsuarioSignUp;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfrimPassword;
    @FXML
    private ComboBox<String> cbCarreras;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnCleanSignUp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> Carreras = new ArrayList<>();
        Collections.addAll(Carreras, new String[]{"probando","1","2","3"});
        //cbCarreras.setItems(FXCollections.observableArrayList()); // No sé como emterle datos a ese comboBox tan raro
        cbCarreras.setItems(FXCollections.observableArrayList(Carreras));
        
    }    
    private void setValues(Connection con ,String user, String password, String carrera) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO users values (?,?,?)");
        ps.setString(1, user);
        ps.setString(2, password);
        ps.setString(3, carrera);
        ps.executeUpdate();
        System.out.println("Agregado correctamente");
    }
    @FXML
    
    private void Register(ActionEvent event) throws SQLException, ClassNotFoundException {
       if(txtPassword.getText().equals(txtConfrimPassword.getText())){
           Connection bd = Conexion.getConexion();
           setValues(bd,txtUsuarioSignUp.getText(), txtPassword.getText(), "Carrera");
       }else{
           anuncio.Error("Las contraseñas no concuerdan");
       }
    }

    @FXML
    private void MostrarCarrerasComboBox(ActionEvent event) {
        
    }
    
}
