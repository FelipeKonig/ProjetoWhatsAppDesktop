package br.edu.ifsc.proj2;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlterarNumeroController {

	@FXML
	private TextField txtNumeroVelho;

	@FXML
	private TextField txtNumeroNovo;

	@FXML
	private JFXButton btAlterarNumero;

	@FXML
	void alterarNumero(ActionEvent event) throws IOException {
		boolean buscaNumero = false;
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		em.getTransaction().begin();

		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				if (usuarioLogado.getNumero().contentEquals(txtNumeroVelho.getText())) {
					buscaNumero = true;
					usuarioLogado.setNumero(txtNumeroNovo.getText());
				}
			}
		}
		if (buscaNumero == false) {
			Alert errorALert = new Alert(AlertType.ERROR);
			errorALert.setContentText("O número que você digitou está errado");
			errorALert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Número atualizado, entre novamente para ter seu perfil atualizado com seu número novo");
			alert.showAndWait();
		}
		em.getTransaction().commit();
		em.close();
	}

}
