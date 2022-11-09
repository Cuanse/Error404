/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

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
    
}
