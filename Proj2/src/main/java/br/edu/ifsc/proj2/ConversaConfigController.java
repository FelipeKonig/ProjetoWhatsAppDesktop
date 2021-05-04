package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConversaConfigController implements Initializable {

	public ObservableList<String> listPopup = FXCollections.observableArrayList("Sem pop-up",
			"Apenas com a tela 'ligada' ", "Apenas com a tela 'desligada'", "Sempre mostrar notificação");

	public ObservableList<String> listSom = FXCollections.observableArrayList("Silencioso", "Acorn", "Cystal",
			"Life's Good", "Maple");

	@FXML
	private ImageView iconVoltar;

	@FXML
	private JFXToggleButton btConfirmaLeitura;

	@FXML
	private JFXToggleButton btVistoUltimo;

	@FXML
	private JFXComboBox<String> cbSomNotificacao;

	@FXML
	private JFXComboBox<String> cbSomPopUp;

	@FXML
	private JFXCheckBox cbSomConversa;

	@FXML
	private JFXButton btSalvarAlteracoes;

	@FXML
	void iconVoltar(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Configuracoes.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btSalvarAlteracoes.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void btVoltar(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Configuracoes.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btSalvarAlteracoes.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void btSalvarAteracoesClicado(ActionEvent event) {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		em.getTransaction().begin();
		
		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				usuarioLogado.setSomNotificacao(cbSomNotificacao.getValue());
				usuarioLogado.setNotifPopup(cbSomPopUp.getValue());
				if (cbSomConversa.isSelected()) {
					usuarioLogado.setSonsConversa(true);
				} else {
					usuarioLogado.setSonsConversa(false);
				}
				if (btConfirmaLeitura.isSelected()) {
					usuarioLogado.setConfirmaLeitura(true);
				} else {
					usuarioLogado.setConfirmaLeitura(false);
				}

				if (cbSomConversa.isSelected()) {
					usuarioLogado.setSonsConversa(true);
				} else {
					usuarioLogado.setSonsConversa(false);
				}

				if (btVistoUltimo.isSelected()) {
					usuarioLogado.setVistoUltimo(true);
				} else {
					usuarioLogado.setVistoUltimo(false);
				}

				

				em.getTransaction().commit();
				em.close();

			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addComboBox();

	}

	void addComboBox() {
		cbSomNotificacao.setItems(listSom);
		cbSomPopUp.setItems(listPopup);
	}
	

}