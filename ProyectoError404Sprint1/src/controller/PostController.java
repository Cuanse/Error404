/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class PostController implements Initializable {
    private Connection bd;
    @FXML
    private Button btnInicioPost;
    @FXML
    private Button btnPerfilPost;
    @FXML
    private Button btnCrearPost;
    @FXML
    private Button btnSalirPost;
    @FXML
    private Button btnPref1;
    @FXML
    private Button btnPref2;
    @FXML
    private Button btnPref3;
    @FXML
    private Button btnPrevPost;
    @FXML
    private Button btnNextPost;
    @FXML
    private Label lblTittlePost1;
    @FXML
    private Label lblTextPost1;
    @FXML
    private Label lblTittlePost2;
    @FXML
    private Label lblTextPost2;
    @FXML
    private VBox vBox1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Clicked(MouseEvent event) {
    }

    @FXML
    private void Vbox1Clicked(MouseEvent event) {
    }
    
}
