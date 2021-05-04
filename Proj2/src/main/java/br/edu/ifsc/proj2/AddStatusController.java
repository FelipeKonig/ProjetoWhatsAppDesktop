package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.transaction.Transactional.TxType;

import com.jfoenix.controls.JFXButton;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Status;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddStatusController{
	
	@FXML
	private ImageView iconAddImagem;

	@FXML
	private Label btAddImagem;

	@FXML
	private TextArea txtStatus;

	@FXML
	private JFXButton btEnviarStatus;

	@FXML
	private JFXButton btVisualizarStatus;

	@FXML
	void addStatus(ActionEvent event) {
		
		if(txtStatus.getText().contentEquals("")) {
			Alert mensagem = new Alert(AlertType.ERROR);
			mensagem.setContentText("Status vazio");
			mensagem.showAndWait();
			
		}
		else {
		
			EntityManager em = Conn.getEntityManager();
			Usuario usuario = LoginController.getUsuarioLogado();
			em.find(Usuario.class, usuario.getId());
			Status statusUsuario = new Status(txtStatus.getText(), usuario.getNome());
			usuario.getStatus().add(statusUsuario);
			em.getTransaction().begin();
			System.out.println(usuario.getNome());
			
			em.merge(usuario);
			System.out.println(usuario.getStatus());
			em.getTransaction().commit();
			
			em.close();
			
			Alert mensagem = new Alert(AlertType.INFORMATION);
			mensagem.setContentText("Status Enviado");
			mensagem.showAndWait();
		}
	}

	@FXML
	void adicionarImagem(MouseEvent event) {
		Alert mensagem = new Alert(AlertType.INFORMATION);
		mensagem.setContentText("Não está disponível no momento");
		mensagem.showAndWait();
	}

	@FXML
	void visualizarStatus(ActionEvent event) throws IOException {
		String nome = LoginController.getUsuarioLogado().getNome();
		String textoStatus = txtStatus.getText();
		MostrarStatusController.setNome(nome);
		MostrarStatusController.setTextoStatus(textoStatus);
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MostrarStatus.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
}
