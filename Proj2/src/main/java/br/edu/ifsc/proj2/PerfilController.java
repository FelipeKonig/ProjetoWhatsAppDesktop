package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PerfilController implements Initializable {
	
	@FXML
	private ImageView iconVoltar;

	@FXML
	private TextArea txtRecado;

	@FXML
	private JFXButton btConfirmarRecado;

	@FXML
	private TextField txtNome;

	@FXML
	private JFXButton btConfirmarNome;

	@FXML
	private TextField txtNumero;

	@FXML
	void btVoltar(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btConfirmarNome.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void alterarNome(ActionEvent event) {
		if (txtNome.getText() != null) {
			EntityManager em = Conn.getEntityManager();
			em = Conn.getEntityManager();
			List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
			
			em.getTransaction().begin();
			
			for (Usuario u : users) {
				if (u.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
					System.out.println("Encontrou");
					u.setNome(txtNome.getText());
				}
			}
			em.getTransaction().commit();
			em.close();
		} 
		else {
			Alert errorALert = new Alert(AlertType.ERROR);
			errorALert.setContentText("O nome não pode ser nulo");
			errorALert.showAndWait();
		}
	}
	
	@FXML
	public void addRecado(ActionEvent event) {
		EntityManager em = Conn.getEntityManager();
		em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		
		em.getTransaction().begin();
		
		for (Usuario u : users) {
			if (u.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				u.setRecado(txtRecado.getText());
				
				em.getTransaction().commit();
				
				em.close();
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		procurarUsuario();

	}
	
	public void procurarUsuario() {
		EntityManager em = Conn.getEntityManager();
		em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		for (Usuario u : users) {
			if (u.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				
				u.getRecado();
				u.getNome();
				u.getNumero();
				txtRecado.setText(u.getRecado());
				txtNome.setText(u.getNome());
				txtNumero.setText(u.getNumero());
				em.close();
			}
		}
	}
}