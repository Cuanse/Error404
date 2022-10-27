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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class SignInFormController implements Initializable {

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
    }    

    @FXML
    private void LogIn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Conexion bd = new Conexion();
        bd.SearchUser(txtUserSignIn.getText(), txtPasswordSignInMask.getText());
    }
    
}
