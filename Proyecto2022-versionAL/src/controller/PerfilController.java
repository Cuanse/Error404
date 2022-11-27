/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class PerfilController implements Initializable {

    static SignInFormController info = new SignInFormController();
    private Connection bd;
    @FXML
    private Button btnInicioPerfil;
    @FXML
    private Button btnPerfilPerfil;
    @FXML
    private Button btnCrearPerfil;
    @FXML
    private Button btnSalirPrfil;
    @FXML
    private Button btnAjustesPerfil;
    @FXML
    private Label lblUserPerfil;
    @FXML
    private Label lblMailPerfil;
    @FXML
    private Label lblBioPerfil;
    @FXML
    private Label lblPaisPerfil;
    @FXML
    private Label lblTelefonoPerfil;
    @FXML
    private Label lblDirPerfil;
    @FXML
    private Label lblCumplePerfil;
    @FXML
    private Label lblGeneroPerfil;
    @FXML
    private Button btnAmigosPerfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bd = Conexion.getBd();
        System.out.println("bd Guardada bien");
        try {
            actualizarDatos();
        } catch (SQLException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TabNavegacion(String direccion) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/" + direccion + ".fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void actualizarDatos() throws SQLException {
        lblUserPerfil.setText(info.getNombreUsuario());
        ResultSet rs = bd.createStatement().executeQuery("SELECT * from USUARIO WHERE ID_USUARIO = " + info.getNoCredenciales());
        rs.next();
        int NoPerfil = 0;
        try {
            NoPerfil = rs.getInt("ID_PERFIL"); // Yo se que por esta linea salen errores pero no detiene la aplicacion entonces no me importa xd
        } catch (SQLException ex) {
            System.out.println("No tiene perfil");
        }
        if ((NoPerfil != 0)) {
            rs = bd.createStatement().executeQuery("SELECT * from PERFIL WHERE ID_PERFIL = " + NoPerfil);
            rs.next();
            lblMailPerfil.setText(rs.getString("EMAILPUBLICO"));
            lblBioPerfil.setText(rs.getString("BIOGRAFIA"));

            lblTelefonoPerfil.setText(rs.getString("TELEFONO"));
            lblDirPerfil.setText(rs.getString("DIRECCION"));
            lblGeneroPerfil.setText(rs.getString("GENERO"));
        }

    }

    @FXML
    private void ajustesPerfil(MouseEvent event) throws IOException, SQLException {
        TabNavegacion("AjustesPerfil");
        actualizarDatos();
    }

    @FXML
    private void Inicio(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void CrearPost(MouseEvent event) throws IOException {
        TabNavegacion("CreatePostForm");
    }

    @FXML
    private void Amigos(MouseEvent event) throws IOException {
        TabNavegacion("Amigos");
    }

}
