package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;

import br.edu.ifsc.proj2.model.Chamada;

import br.edu.ifsc.proj2.database.Conn;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChamadaController implements Initializable {

	private static String contatoLigacao;
	private boolean verificaContato = false;

	@FXML
	private ImageView iconVoltar;

	@FXML
	private ListView<String> listContatos;

	@FXML
	private TextField txtContato;

	@FXML
	private ImageView iconBuscar;

	@FXML
	private ImageView iconTelefone;

	@FXML
	private ImageView iconVideo;

	@FXML
	private Text txtTipoLigacao;

	@FXML
	private JFXButton btChamada;

	@FXML
	private Label lblTipoLigacao;

	@FXML
    void ApagarChamada(MouseEvent event) {
		Chamada chamada;
		
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		
		
		if(listContatos.getSelectionModel().getSelectedItem() != null) {
			for (Usuario u : users) {
				if (u.getNome().contentEquals(LoginController.getUsuarioLogado().getNome())) {
					for (int i = 0; i < u.quantidadeChamadas(); i++) {
						String chamadaNome = listContatos.getSelectionModel().getSelectedItem();
						if(u.getChamadas().get(i).verificaContato(chamadaNome) == true) {
							chamada = u.getChamadas().get(i);
							em.getTransaction().begin();
							u.getChamadas().remove(chamada);
							em.getTransaction().commit();
						} 
					}
				}
			}
			
			em.close();
			preencherLista();
		}
    }

	@FXML
	void Chamada(MouseEvent event) {
		lblTipoLigacao.setText("Ligação");
		lblTipoLigacao.setVisible(true);
	}

	@FXML
	void FazerChamada(ActionEvent event) throws IOException {
		if (verificaContato == true && lblTipoLigacao.isVisible()) {
			addChamada();

			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Ligacao.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();
		} else {
			if (verificaContato == false) {
				Alert errorALert = new Alert(AlertType.ERROR);
				errorALert.setContentText("Informe um contato válido");
				errorALert.showAndWait();
			} else {
				Alert errorALert = new Alert(AlertType.ERROR);
				errorALert.setContentText("Informe o tipo de ligação");
				errorALert.showAndWait();
			}
		}
	}

	@FXML
	void LigarChamadaVideo(MouseEvent event) throws IOException {
		lblTipoLigacao.setText("Chamada de vídeo");
		lblTipoLigacao.setVisible(true);
	}

	@FXML
	void btVoltar(MouseEvent event) throws IOException {

		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btChamada.getScene().getWindow();
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
					if (txtContato.getText().contentEquals(u.getContato().get(i).getNome())) {
						System.out.println(u.getContato().get(i).getNome());
						contatoLigacao = u.getContato().get(i).getNome();
						verificaContato = true;
						break;
					}
				}
			}
		}
		em.close();
	}

	public static String getContatoLigacao() {
		return contatoLigacao;
	}

	public void addChamada() {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		
		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				System.out.println(usuarioLogado.getNome());
				em.getTransaction().begin();
				Chamada chamada = new Chamada(lblTipoLigacao.getText(), contatoLigacao);
				em.persist(chamada);
				em.getTransaction().commit();
				System.out.println(chamada.getContato() + "," + chamada.getTipoLigacao());
				em.getTransaction().begin();
				usuarioLogado.getChamadas().add(chamada);
				em.getTransaction().commit();
			}
		}
		em.close();
		preencherLista();
	}

	public void preencherLista() {
		
		listContatos.getItems().clear();
		
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		for (Usuario u : users) {
			if (u.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				for (int i = 0; i < u.quantidadeChamadas(); i++) {
					listContatos.getItems().add(u.getChamadas().get(i).toString());
				}
			}
		}
		em.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherLista();

	}	
}
