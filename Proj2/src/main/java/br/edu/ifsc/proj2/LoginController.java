package br.edu.ifsc.proj2;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	public static Usuario usuarioLogado;

	@FXML
	private TextField txtNumero;

	@FXML
	private JFXButton btAvancar;

	@FXML
	private JFXButton btCadastrar;

	public void clique(ActionEvent e) throws IOException {
		Button btClicado = (Button) e.getSource();
		if (btClicado == btCadastrar) {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Cadastro.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();
			Stage stage2 = (Stage) btCadastrar.getScene().getWindow();
			stage2.close();
		} else {
			if (verificaLogin() == true) {
				Stage stage = new Stage();
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				stage.setScene(new Scene(root));
				stage.show();
				Stage stage2 = (Stage) btAvancar.getScene().getWindow();
				stage2.close();
				
			} else {
				Alert errorALert = new Alert(AlertType.ERROR);
				errorALert.setContentText("Número não cadastrado");
				errorALert.showAndWait();
			}
		}
	}

	public boolean verificaLogin() {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		System.out.println("Usuários:");
		for (Usuario u : users) {
			if (txtNumero.getText().contentEquals(u.getNumero())) {
				usuarioLogado = u;
				em.close();
				return true;
			}
		}
		em.close();
		return false;			
	}

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	
	
}