package br.edu.ifsc.proj2;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConfiguracoesController {

    @FXML
    private ImageView iconVoltar;

    @FXML
    private Label btConversa;

    @FXML
    private ImageView iconChat;

    @FXML
    private Label btDados;

    @FXML
    private ImageView iconDados;

    @FXML
    private ImageView iconPhone;

    @FXML
    private Label btChamada;

    @FXML
    private ImageView iconConfigGeral;

    @FXML
    private JFXButton btSolicitarDados;

    @FXML
    private JFXButton btApagarConta;

    @FXML
    private JFXButton btAlterarNumero;

    @FXML
    private JFXToggleButton btNotificSegunranca;

    @FXML
    private ImageView iconAjuda;

    @FXML
    private Label btAjuda;

    @FXML
    void btAjudaClicado(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Ajuda.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
    }

    @FXML
    void btAlterarNumeroClicado(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AlterarNumero.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
    }

    @FXML
    void btApagarContaClicado(ActionEvent event) throws IOException {
    	EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				if(usuarioLogado.getContato() != null)
					usuarioLogado.getContato().clear();
				em.getTransaction().begin();
				em.remove(usuarioLogado);

				em.getTransaction().commit();
				em.close();
			}
		}
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btDados.getScene().getWindow();
		stage2.close();
    }

    @FXML
    void btChamadaClicado(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ChamadasConfig.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
    }

    @FXML
    void btConversaClicado(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ConversaConfig.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btDados.getScene().getWindow();
		stage2.close();
    }

    @FXML
    void btDadosClicado(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DadosConfig.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
    }

    @FXML
    void btNotificSegurancaClicado(ActionEvent event) {

    }

    @FXML
    void btSolicitarDadosClicado(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TabelaUsuarios.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAjuda.getScene().getWindow();
		stage2.close();
    }

    @FXML
    void btVoltar(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btDados.getScene().getWindow();
		stage2.close();
    }

}