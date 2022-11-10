/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Alerta;
import Modelo.Conexion;
import static controller.PerfilController.info;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class PostsFormController implements Initializable {
    private Connection bd;
    PostController info = new PostController();
    @FXML
    private Text txtTitle;
    @FXML
    private Text txtContent;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblAutor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bd = Conexion.getBd();
        System.out.println("bd Guardada bien");    
        int n = info.getPostActual();
        txtTitle.setText(info.getForos()[info.getForoActual()].getPosts()[((info.getPagActual()-1)*2)+n].getTitulo());
        txtContent.setText(info.getForos()[info.getForoActual()].getPosts()[((info.getPagActual()-1)*2)+n].getContenido());
        lblDate.setText(info.getForos()[info.getForoActual()].getPosts()[((info.getPagActual()-1)*2)+n].getCreacion().toString());
        lblAutor.setText(info.getForos()[info.getForoActual()].getPosts()[((info.getPagActual()-1)*2)+n].getAutor());
    }    

    @FXML
    private void BorrarPost(MouseEvent event) throws SQLException {
        ResultSet rs = bd.createStatement().executeQuery("SELECT * from USUARIO WHERE ID_USUARIO = " + info.getNoCredenciales());
        rs.next();
        if(rs.getBoolean("ESADMIN") | rs.getString("NOMBREUSUARIO").equals(lblAutor.getText()) ){
            PreparedStatement ps = bd.prepareStatement("UPDATE POST SET ENABLED = false WHERE ID_POST = " + info.getForos()[info.getForoActual()].getPosts()[((info.getPagActual()-1)*2)+info.getPostActual()].getID_POST());
            ps.executeUpdate();
            new Alerta().Information("Eliminado Correctamente");
            // Lo siguiente es para salir automaticamente del post
            Stage stage = new Stage();
            Node source = (Node) event.getSource();
            stage = (Stage) source.getScene().getWindow();
            stage.close();
        }else{
            new Alerta().Information("NO eres Admin o el dueño del post");
        }
    }
    
}
