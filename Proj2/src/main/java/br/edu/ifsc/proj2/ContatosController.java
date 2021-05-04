package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXListView;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ContatosController implements Initializable {
	

    @FXML
    private Label btAddContato;

    @FXML
    private ImageView iconAddContato;

    @FXML
    private JFXListView<String> listContatos;
    
    @FXML
    private ImageView iconVoltar;

    @FXML
    void AdicionarContato(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddContato.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAddContato.getScene().getWindow();
		stage2.close();
    }

    @FXML
    void ContatoSelecionado(MouseEvent event) throws IOException {
    	
    	selecionarContato();
    	
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PerfilContato.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAddContato.getScene().getWindow();
		stage2.close();
    }
    
    @FXML
    void btVoltar(MouseEvent event) throws IOException {
    	
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAddContato.getScene().getWindow();
		stage2.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addListContatos();
		
	}
	
	public void addListContatos() {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		for (Usuario u : users) {
			if (u.getNome().contentEquals(LoginController.getUsuarioLogado().getNome())) {
				System.out.println(u.getNome());
				for (int i = 0; i < u.quantidadeContatos(); i++) {
					listContatos.getItems().add(u.getContato().get(i).getNome());
				}
			}
		}
		em.close();	
	}
	
	void selecionarContato() {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		for (Usuario contato : users) {
			if (contato.getNome().contentEquals(listContatos.getSelectionModel().getSelectedItem())) {
				PerfilContatoController.setNome(contato.getNome());
				PerfilContatoController.setNumero(contato.getNumero());
				PerfilContatoController.setRecado(contato.getRecado());
			}
		}
		em.close();
	}


	
	

}