package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlterarContatoController implements Initializable {

	public ObservableList<String> listTipos = FXCollections.observableArrayList("Cel", "Trabalho", "Residencial",
			"Principal", "Fax Comercial");

	@FXML
	private JFXComboBox<String> cbtipoNumero;

	@FXML
	private ImageView iconVoltar;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextArea txtComentrario;

	@FXML
	private JFXToggleButton btLigacao;

	@FXML
	private JFXToggleButton btMensagem;

	@FXML
	private JFXToggleButton btChamadaVIdeo;

	@FXML
	private JFXToggleButton btAudio;

	@FXML
	private JFXToggleButton btFicheiro;

	@FXML
	private JFXToggleButton btNotificacao;

	@FXML
	private JFXButton btConfirmar;

	@FXML
	private JFXButton btApagar;

	@FXML
	void apagarContato(ActionEvent event) {
		boolean buscaContato = false;
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				for (int i = 0; i < usuarioLogado.quantidadeContatos(); i++) {
					if (usuarioLogado.getContato().get(i).getNumero().contentEquals(txtNumero.getText())) {
						buscaContato = true;
						Usuario contato = usuarioLogado.getContato().get(i);

						System.out.println(contato.getNome());
						em.getTransaction().begin();
						usuarioLogado.getContato().remove(contato);
						em.getTransaction().commit();

						break;
					}
				}
			}
		}
		if (buscaContato == false) {
			Alert errorALert = new Alert(AlertType.ERROR);
			errorALert.setContentText("Este usuário não existe");
			errorALert.showAndWait();
		}
		em.close();
	}

	@FXML
	void btVoltar(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btApagar.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void confirmarContato(ActionEvent event) {
		boolean buscaContato = false;
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		em.getTransaction().begin();

		for (Usuario usuario : users) {
			if (usuario.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				for (int i = 0; i < usuario.quantidadeContatos(); i++) {
					if (usuario.getContato().get(i).getNumero().contentEquals(txtNumero.getText())) {
						buscaContato = true;
						Usuario contato = usuario.getContato().get(i);
						contato.setComentario(txtComentrario.getText());
						contato.setTipoNumero(cbtipoNumero.getValue());

						if (btLigacao.isSelected()) {
							contato.setLigacao(true);
						} else {
							contato.setLigacao(false);
						}

						if (btChamadaVIdeo.isSelected()) {
							contato.setChamadaVideo(true);
						} else {
							contato.setChamadaVideo(false);
						}

						if (btFicheiro.isSelected()) {
							contato.setFicheiro(true);
						} else {
							contato.setFicheiro(false);
						}

						if (btMensagem.isSelected()) {
							contato.setMensagem(true);
						} else {
							contato.setMensagem(false);
						}

						if (btAudio.isSelected()) {
							contato.setAudio(true);
						} else {
							contato.setAudio(false);
						}

						if (btNotificacao.isSelected()) {
							contato.setNotificacao(true);
						} else {
							contato.setNotificacao(false);
						}
					}
				}

				em.getTransaction().commit();

			}
		}
		if (buscaContato == false) {
			Alert errorALert = new Alert(AlertType.ERROR);
			errorALert.setContentText("Este usuário não existe");
			errorALert.showAndWait();
		}
		em.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbtipoNumero.setItems(listTipos);

	}

}
