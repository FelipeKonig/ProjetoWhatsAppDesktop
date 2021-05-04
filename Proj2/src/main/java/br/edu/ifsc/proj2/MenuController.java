package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Chamada;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController implements Initializable {

	private ObservableList<String> menuUsuario = FXCollections.observableArrayList("Perfil", "Configurações",
			"Sair da conta");
	private ObservableList<String> menuContato = FXCollections.observableArrayList("Dados do contato");

	@FXML
	private JFXComboBox<String> cbMenuUsuario;

	@FXML
	private TextField txtBuscarContato;

	@FXML
	private JFXComboBox<String> cbMenuContato;

	@FXML
	private TextArea txtMensagem;

	@FXML
	private Button btEnviar;

	@FXML
	private ImageView iconLupa2;

	@FXML
	private ImageView iconLupa1;

	@FXML
	private ImageView iconLigacao;

	@FXML
	private ImageView iconEmotion;

	@FXML
	private AnchorPane paneConversa;

	@FXML
	private Label lblContato;

	@FXML
	private JFXListView<String> listContatos;
	
	@FXML
	private TextArea msg1;

	@FXML
	private TextArea msg2;

	@FXML
	private TextArea msg3;

	@FXML
	private TextArea msg4;

	@FXML
	private ImageView iconStatus;

	@FXML
	private ImageView btAddContato;

	@FXML
	public void contatoSelecionado(MouseEvent event) {
		System.out.println(listContatos.getSelectionModel().getSelectedItem());
		lblContato.setText(listContatos.getSelectionModel().getSelectedItem());
		paneConversa.setVisible(false);
	}

	@FXML
	void verContatos(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Contatos.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAddContato.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void btChamadas(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Chamadas.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAddContato.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void verStatus(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Status.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void opMenuUsuario(ActionEvent event) throws IOException {
		System.out.println(cbMenuUsuario.getValue());
		if (cbMenuUsuario.getValue().contentEquals("Perfil")) {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Perfil.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();
			Stage stage2 = (Stage) cbMenuUsuario.getScene().getWindow();
			stage2.close();
		}
		if (cbMenuUsuario.getValue().contentEquals("Configurações")) {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Configuracoes.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();
			Stage stage2 = (Stage) cbMenuUsuario.getScene().getWindow();
			stage2.close();
		}
		if (cbMenuUsuario.getValue().contentEquals("Sair da conta")) {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();
			Stage stage2 = (Stage) cbMenuUsuario.getScene().getWindow();
			stage2.close();
		}
	}

	@FXML
	void opMenuContato(ActionEvent event) throws IOException {

		selecionarContato();

		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PerfilContato.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) cbMenuUsuario.getScene().getWindow();
		stage2.close();
	}

	@FXML
	void buscarContato(MouseEvent event) {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		for (Usuario u : users) {
			if (u.getNome().contentEquals(LoginController.getUsuarioLogado().getNome())) {
				System.out.println(u.getNome());
				for (int i = 0; i < u.quantidadeContatos(); i++) {
					if (txtBuscarContato.getText().contentEquals(u.getContato().get(i).getNome())) {
						lblContato.setText(u.getContato().get(i).getNome());
						paneConversa.setVisible(false);
						break;
					}
				}
			}
		}
		em.close();
	}

	@FXML
	void enviarMsg(ActionEvent event) {
		if (msg1.getText().contentEquals("")) {
			msg1.setText(txtMensagem.getText());
			msg1.setVisible(true);
			msg2.setVisible(true);
		} else {
			msg3.setText(txtMensagem.getText());
			msg3.setVisible(true);
			msg4.setVisible(true);
		}
		txtMensagem.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addComboBox();
		addListContatos();

	}

	public void addComboBox() {
		cbMenuUsuario.setItems(menuUsuario);
		cbMenuContato.setItems(menuContato);
	}

	public void addListContatos() {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		for (Usuario u : users) {
			if (u.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
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
			if (contato.getNome().contentEquals(lblContato.getText())) {
				PerfilContatoController.setNome(contato.getNome());
				PerfilContatoController.setNumero(contato.getNumero());
				PerfilContatoController.setRecado(contato.getRecado());
			}
		}
		em.close();
	}
}