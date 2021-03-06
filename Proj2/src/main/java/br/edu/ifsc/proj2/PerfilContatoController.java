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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PerfilContatoController implements Initializable {
	
	private static String nome,numero,recado; 

    @FXML
    private ImageView iconVoltar;

    @FXML
    private ImageView iconUsuario;

    @FXML
    private Label txtNome;

    @FXML
    private Text txtRecado;

    @FXML
    private Text txtNumero;

    @FXML
    private JFXButton btAlterarContato;

    @FXML
    private JFXButton btApagarContato;

    @FXML
    void alterarContato(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AlterarContato.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAlterarContato.getScene().getWindow();
		stage2.close();
    }

    @FXML
    void visualizarContato(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TabelaContatos.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) iconVoltar.getScene().getWindow();
		stage2.close();
    }

    @FXML
    void btVoltar(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) btAlterarContato.getScene().getWindow();
		stage2.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNome.setText(nome);
		txtNumero.setText(numero);
		txtRecado.setText(recado);
	}

	public static void setNome(String nome) {
		PerfilContatoController.nome = nome;
	}

	public static void setNumero(String numero) {
		PerfilContatoController.numero = numero;
	}

	public static void setRecado(String recado) {
		PerfilContatoController.recado = recado;
	}

}
