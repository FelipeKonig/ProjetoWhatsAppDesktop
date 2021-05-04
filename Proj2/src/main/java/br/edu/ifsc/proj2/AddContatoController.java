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

public class AddContatoController implements Initializable {

	public ObservableList<String> listTipos = FXCollections.observableArrayList("Cel", "Trabalho", "Residencial",
			"Principal", "Fax Comercial");

	@FXML
	private ImageView iconVoltar;

	@FXML
	private TextField txtNome;

	@FXML
	private JFXComboBox<String> cbTiposNumero;

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
	void btVoltar(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAudio.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void confirmarContato(ActionEvent event) {
		verificaAdd();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbTiposNumero.setItems(listTipos);

	}

	void verificaAdd() {
		boolean buscaContato = false;
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		em.getTransaction().begin();

		for (Usuario contato : users) {
			if (contato.getNumero().contentEquals(txtNumero.getText())) {
				buscaContato = true;
				for (Usuario usuarioLogado : users) {
					if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
						usuarioLogado.getContato().add(contato);
						contato.setComentario(txtComentrario.getText());
						contato.setTipoNumero(cbTiposNumero.getValue());
						
						if (btLigacao.isSelected()) {
							contato.setLigacao(true);
						}else {
							contato.setLigacao(false);
						}
						
						if(btChamadaVIdeo.isSelected()) {
							contato.setChamadaVideo(true);
						}else {
							contato.setChamadaVideo(false);
						}
						
						if(btFicheiro.isSelected()) {
							contato.setFicheiro(true);
						}else {
							contato.setFicheiro(false);
						}
						
						if(btMensagem.isSelected()) {
							contato.setMensagem(true);
						}else {
							contato.setMensagem(false);
						}
						
						if(btAudio.isSelected()) {
							contato.setAudio(true);
						}else {
							contato.setAudio(false);
						}
						
						if(btNotificacao.isSelected()) {
							contato.setNotificacao(true);
						}else {
							contato.setNotificacao(false);
						}

						em.getTransaction().commit();

					}
				}

			}
		}
		if(buscaContato == false) {
			Alert errorALert = new Alert(AlertType.ERROR);
			errorALert.setContentText("Este usuário não existe");
			errorALert.showAndWait();
		}		
		em.close();
	}

}
