package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatusController implements Initializable {
	private static String nomeContato;
	
	@FXML
	private ImageView iconAddStatus;

	@FXML
	private ListView<String> listStatus;

	@FXML
	void StatusSelecionado(MouseEvent event) throws IOException {
		
		if(listStatus.getSelectionModel().getSelectedItem() != null) {		
			EntityManager em = Conn.getEntityManager();
			List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
			for (Usuario contato : users) {
				if (contato.getNome().contentEquals(listStatus.getSelectionModel().getSelectedItem())) {
					System.out.println(contato.getNome());
					nomeContato = contato.getNome();
					MostrarStatusContatoController.setI(1);
					
					for (int i = 0; i < 1; i++) {
						System.out.println(contato.getStatus().get(i).getTxtStatus());
						MostrarStatusContatoController.setNome(contato.getNome());
						MostrarStatusContatoController.setTexto(contato.getStatus().get(i).getTxtStatus());
					}
					
					Stage stage = new Stage();
					FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MostrarStatusContato.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					stage.setScene(new Scene(root));
					stage.show();
				}
			}
			em.close();
		}
	}

	@FXML
	void addStatus(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddStatus.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherLista();

	}

	public void preencherLista() {
		
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		for (Usuario u : users) {
			if (u.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				for (int i = 0; i < u.quantidadeContatos(); i++) {
					Usuario contato = u.getContato().get(i);
					if (contato.quantidadeStatus() > 0) {
							listStatus.getItems().add(contato.getNome());
					}
				}
			}
			em.close();
		}
	}

	public static String getNomeContato() {
		return nomeContato;
	}
	
	
}
