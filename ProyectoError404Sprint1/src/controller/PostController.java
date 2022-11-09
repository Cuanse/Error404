/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Alerta;
import Modelo.Conexion;
import Modelo.Foro;
import Modelo.Post;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class PostController implements Initializable {

    private Connection bd;
    private Foro foros[];
    private Alerta alarma = new Alerta();
    private int foroActual = 0; // Ojo NO es el Id_FORO , sino el foro en el arreglo foros
    private int pagActual = 1;
    SignInFormController info = new SignInFormController();
    private int noCredenciales = info.getNoCredenciales();

    public int getNoCredenciales() {
        return noCredenciales;
    }

    public void setNoCredenciales(int noCredenciales) {
        this.noCredenciales = noCredenciales;
    }

    public int getForoActual() {
        return foroActual;
    }

    public void setForoActual(int foroActual) {
        this.foroActual = foroActual;
    }

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
    @FXML
    private GridPane GridPost;
    @FXML
    private Pane PanePost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bd = Conexion.getBd();
        System.out.println("bd Guardada bien");
        System.out.println(info.getNoCredenciales());
        /* //probando unas cosas nada mas
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(PanePost.getScene());
        stage.show*/

        int n = 3;
        //Código cuando tengamos bd SQL para contar cuantos foros existen
        ResultSet rs = null; // tocaba inicializarla en algo por los posibles errores
        try {
            rs = bd.createStatement().executeQuery("SELECT COUNT(*) FROM FORO");
            rs.next();
            n = rs.getInt("COUNT(*)"); // Esto es solo convertir el result set a int
            rs = bd.createStatement().executeQuery("SELECT * FROM FORO");
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        foros = new Foro[n];

        int i = 0; // contador que recorre los foros.
        try {
            /*Tecnicamente estamos cargando TODOS los foros y TODOS los posts que contienen, lo cual no es optimo pero que importa*/
 /*ALGO SUCEDE CON RS3 pero no se que es*/
            while (rs.next()) {
                foros[i] = new Foro(rs.getString("Nombre"), rs.getString("Categoria"));
                ResultSet rs2 = bd.createStatement().executeQuery("SELECT * FROM POST WHERE ID_FORO = " + rs.getInt("ID_FORO")); // quizá es getInt y falta sort by Date
                ResultSet rs3 = bd.createStatement().executeQuery("SELECT COUNT(*) from (SELECT * FROM POST WHERE ID_FORO = " + rs.getString("ID_FORO") + ") as SUBQUERY");
                //n = rs3.getInt("Count(*)"); 
                n = 1;
                //System.out.println("SELECT COUNT(*) from (SELECT * FROM POST WHERE ID_FORO = " + rs.getString("ID_FORO")+") as SUBQUERY");
                //System.out.print(rs3.getInt("COUNT(*)"));
                if (n == 0) {
                    alarma.Information("No hay Posts en este foro: " + rs.getString("Nombre"));
                } else {
                    Post posts[] = new Post[10];

                    // Ingresamos los posts máximo 10
                    for (int j = 0; j < 10; j++) {
                        posts[j] = new Post();
                        if (rs2.next()) {
                            posts[j].setTitulo(rs2.getString("TITULO"));
                            posts[j].setAutor("Autor");
                            posts[j].setContenido(rs2.getString("CONTENIDO"));
                            posts[j].setCreacion(rs2.getTimestamp("CREADOPOST")); // todavia no sé como Diana lo puso en la bd tan raro pero ok
                        }
                    }
                    foros[i].setPosts(posts);
                }

                i++;
            }

            //Cargar los primeros Posts de la primera página
            LoadPosts(foroActual, (pagActual - 1) * 2);
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void LoadPosts(int foro, int page) { // Page es un int medio raro, porque debe ser un numero de ((Nopágina - 1)*2) para saber cual par de posts debe cargar
        int n = foros[foro].getPosts().length;
        if (n == 0) {
            alarma.Information("Este foro no tiene Posts");
        } else if (n == 1) {
            lblTittlePost1.setText(foros[foro].getPosts()[page].getTitulo());
            lblTextPost1.setText(foros[foro].getPosts()[page].getContenido());
        } else {
            lblTittlePost1.setText(foros[foro].getPosts()[page].getTitulo()); // En cada pagina caben 2 posts, pero si no hay 2 posts ó más toca omitir codigo.
            lblTittlePost2.setText(foros[foro].getPosts()[page + 1].getTitulo());

            lblTextPost1.setText(foros[foro].getPosts()[page].getContenido());
            lblTextPost2.setText(foros[foro].getPosts()[page + 1].getContenido());
        }
    }

    @FXML
    private void Clicked(MouseEvent event) {
    }

    @FXML
    private void Vbox1Clicked(MouseEvent event) {
    }

    @FXML
    private void verPerfil(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Perfil.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait(); // Espere a que Perfil dé una respuesta
    }

    @FXML
    private void CrearPost(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/CreatePostForm.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        alarma.Information("Gracias Por la publicación");
        LoadPosts(foroActual, 0);
    }

}
