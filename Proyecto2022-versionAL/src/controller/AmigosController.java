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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class AmigosController implements Initializable {

    @FXML
    private GridPane GridPost;
    @FXML
    private Button btnInicioAmigos;
    @FXML
    private Button btnPerfilAmigos;
    @FXML
    private Button btnCrearAmigos;
    @FXML
    private Button btnAmigosAmigos;
    @FXML
    private Button btnSalirAmigos;
    @FXML
    private ImageView btnAddAmigos;
    @FXML
    private Button btnPrevAmigos;
    @FXML
    private Button btnNextAmigos;
    @FXML
    private Pane PaneAmigos;
    @FXML
    private Label lblNombreAmigos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void verPerfil(MouseEvent event) {
    }

    @FXML
    private void CrearPost(MouseEvent event) {
    }
    
}
