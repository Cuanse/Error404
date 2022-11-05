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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class SignInFormController implements Initializable {
    private Connection bd;
    private Alerta anuncio = new Alerta();
    @FXML
    private VBox panelFormSignIn;
    @FXML
    private TextField txtUserSignIn;
    @FXML
    private PasswordField txtPasswordSignIn;
    @FXML
    private TextField txtPasswordSignInMask;
    @FXML
    private CheckBox checkViewPassSignIn;
    @FXML
    private Button btnSgnIn;
    @FXML
    private Button btnClean;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bd = Conexion.getBd();
        System.out.println("bd Guardada");
    }    

    private void SearchUser(String Username, String Password) throws SQLException, ClassNotFoundException {
        
        
        ResultSet rs = bd.createStatement().executeQuery("SELECT * from users");
        boolean encontrado = true;
        while (rs.next()) {
            encontrado = false;
            if (rs.getString("UserName").equals(Username)) {
                encontrado = true;
                if (rs.getString("Passwrd").equals(Password)) {
                    /* Añadir código de ingreso de sesión e inicializacion de CreatePost Form y mandar la misma conexión "con"*/
                    break;
                } else {
                    anuncio.Information("Contraseña equivocada");
                    break;
                }
            }
        }
        if (!encontrado) {
            anuncio.Error("No existe ese nombre de usuario");
        }

    }

    @FXML
    private void LogIn(MouseEvent event) throws SQLException, ClassNotFoundException {
        if(txtPasswordSignIn.visibleProperty().getValue()){
            SearchUser(txtUserSignIn.getText(), txtPasswordSignIn.getText());
        }else{
            SearchUser(txtUserSignIn.getText(), txtPasswordSignInMask.getText());
        }
        
    }

    @FXML
    private void VerContraClicked(MouseEvent event) {
        if(txtPasswordSignIn.visibleProperty().getValue()){
            txtPasswordSignIn.setVisible(false);
            txtPasswordSignInMask.setVisible(true);
            txtPasswordSignInMask.setText(txtPasswordSignIn.getText());
        }else{
            txtPasswordSignIn.setVisible(true);
            txtPasswordSignInMask.setVisible(false);
            txtPasswordSignIn.setText(txtPasswordSignInMask.getText());
        }
    }

    @FXML
    private void CleantxtFields(MouseEvent event) {
        txtPasswordSignIn.setText("");
        txtPasswordSignInMask.setText("");
        txtUserSignIn.setText("");
    }
    
}
