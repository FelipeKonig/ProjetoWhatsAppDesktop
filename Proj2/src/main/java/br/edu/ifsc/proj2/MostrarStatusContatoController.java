package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MostrarStatusContatoController implements Initializable {

	private static String nome, texto;
	private static int i;

	@FXML
	private TextArea txtStatus;

	@FXML
	private Label txtNome;

	@FXML
	private JFXButton btProximoStatus;

	@FXML
	void ProximoStatus(ActionEvent event) throws IOException {
		
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		for (Usuario contato : users) {
			if(contato.getNome().contentEquals(nome)) {
				int k = contato.quantidadeStatus();
				
				while(i < k) {
					System.out.println(nome);
					
					setTexto(contato.getStatus().get(i).getTxtStatus());
					System.out.println(texto);
					
					i++;
					break;
				}
				Stage stage = new Stage();
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MostrarStatusContato.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				stage.setScene(new Scene(root));
				stage.show();
				Stage stage2 = (Stage) btProximoStatus.getScene().getWindow();
				stage2.close();
			}
		}
		em.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNome.setText(nome);
		txtStatus.setText(texto);

	}

	public static void setNome(String nome) {
		MostrarStatusContatoController.nome = nome;
	}

	public static void setTexto(String texto) {
		MostrarStatusContatoController.texto = texto;
	}

	public void setTxtStatus(TextArea txtStatus) {
		this.txtStatus = txtStatus;
	}

	public void setTxtNome(Label txtNome) {
		this.txtNome = txtNome;
	}

	public void setBtProximoStatus(JFXButton btProximoStatus) {
		this.btProximoStatus = btProximoStatus;
	}

	public static void setI(int i) {
		MostrarStatusContatoController.i = i;
	}

	
}
