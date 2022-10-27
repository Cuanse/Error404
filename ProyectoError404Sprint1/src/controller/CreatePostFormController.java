/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class CreatePostFormController implements Initializable {

    @FXML
    private Button btnInicioCrear;
    @FXML
    private Button btnPerfilCrear;
    @FXML
    private Button btnCrearCrear;
    @FXML
    private Button btnSalirCrear;
    @FXML
    private TextField txtNombreCrear;
    @FXML
    private TextArea txtTemaCrear;
    @FXML
    private Button btnLimpiarCrear;
    @FXML
    private Button btnEnviarCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
