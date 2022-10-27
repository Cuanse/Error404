/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Conexion;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class SignUpFormController implements Initializable {

    @FXML
    private VBox panelFormSignUp;
    @FXML
    private TextField txtUsuarioSignUp;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfrimPassword;
    @FXML
    private ComboBox<?> cbCarreras;
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
    }    

    @FXML
    private void Register(ActionEvent event) throws SQLException, ClassNotFoundException {
       if(txtPassword.getText().equals(txtConfrimPassword.getText())){
           Conexion bd = new Conexion();
           bd.setValues(txtUsuarioSignUp.getText(), txtPassword.getText(), "Carrera");
       }
    }
    
}
