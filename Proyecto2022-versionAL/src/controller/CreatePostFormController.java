/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class CreatePostFormController implements Initializable {
    private Connection bd;
    private PostController info = new PostController();
    @FXML
    private Button btnInicioCrear;
    @FXML
    private Button btnPerfilCrear;
    @FXML
    private Button btnCrearCrear;
    @FXML
    private Button btnAmigosCrear;
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
        bd = Conexion.getBd();
        
    }    

    @FXML
    private void EnviarPost(MouseEvent event) throws SQLException {
        PreparedStatement ps = bd.prepareStatement("INSERT INTO POST (ID_PERFIL,ID_FORO,CONTENIDO,TITULO,ENABLED) VALUES(?,?,?,?,?)");
        ps.setInt(1, info.getNoCredenciales());
        ps.setInt(2,info.getID_foroActual() );        
        ps.setString(3, txtTemaCrear.getText());
        ps.setString(4, txtNombreCrear.getText());
        ps.setBoolean(5, true);
        ps.executeUpdate();
        System.out.println("Agregado correctamente");
        
        Stage stage = new Stage();
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
    
}
