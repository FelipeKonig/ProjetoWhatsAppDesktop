package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DadosUsuario implements Initializable {

	ObservableList<Usuario> listUsuario = FXCollections.observableArrayList();
	

    @FXML
    private TableView<Usuario> idTabela;
    
    @FXML
    private ImageView iconVoltar;

    @FXML
    private TableColumn<Usuario, String> colunmNome;

    @FXML
    private TableColumn<Usuario, String> colunmNumero;

    @FXML
    private TableColumn<Usuario, String> colunmPopup;

    @FXML
    private TableColumn<Usuario, String> columnRecado;

    @FXML
    private TableColumn<Usuario, String> columnBackupWifi;

    @FXML
    private TableColumn<Usuario, String> columnBackupRoaming;

    @FXML
    private TableColumn<Usuario, String> columnBackupDadosMoveis;
    
    @FXML
    void btVoltar(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Configuracoes.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) iconVoltar.getScene().getWindow();
		stage2.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preenchertabela();
	}
	
	public void pegarDados() {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
//
//		for (Usuario usuarioLogado : users) {
//			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
//		
//			}
//		}
		listUsuario.addAll(users);
		em.close();
	}
   
	public void preenchertabela() {
    	
		pegarDados();
    	colunmNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
    	colunmNumero.setCellValueFactory(new PropertyValueFactory<Usuario, String>("numero"));
    	colunmPopup.setCellValueFactory(new PropertyValueFactory<Usuario, String>("notifPopup"));
    	columnRecado.setCellValueFactory(new PropertyValueFactory<Usuario, String>("recado"));
    	columnBackupWifi.setCellValueFactory(new PropertyValueFactory<Usuario, String>("backupWifi"));

    	columnBackupDadosMoveis.setCellValueFactory(new PropertyValueFactory<Usuario, String>("backupDadosMoveis"));
    	columnBackupRoaming.setCellValueFactory(new PropertyValueFactory<Usuario, String>("backupRoaming"));
    	idTabela.setItems(listUsuario);
    	
    }
	
}

