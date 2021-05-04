package br.edu.ifsc.proj2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DadosContato implements Initializable {
	
	ObservableList<Usuario> listContatos = FXCollections.observableArrayList();

    @FXML
    private ImageView iconVoltar;

    @FXML
    private TableView<Usuario> idTabela;

    @FXML
    private TableColumn<Usuario, String> colunmNome;

    @FXML
    private TableColumn<Usuario, String> colunmNumero;

    @FXML
    private TableColumn<Usuario, String> colunmTipoNumero;

    @FXML
    private TableColumn<Usuario, String> columnLigacao;

    @FXML
    private TableColumn<Usuario, String> columnMensagem;

    @FXML
    private TableColumn<Usuario, String> columnFicheiro;

    @FXML
    private TableColumn<Usuario, String> columnComentario;

    @FXML
    void btVoltar(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PerfilContato.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
		Stage stage2 = (Stage) iconVoltar.getScene().getWindow();
		stage2.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preenchertabela();
		
	}
	
public void preenchertabela() {
    	
		pegarDados();
    	colunmNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
    	colunmNumero.setCellValueFactory(new PropertyValueFactory<Usuario, String>("numero"));
    	columnComentario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("comentario"));
    	columnFicheiro.setCellValueFactory(new PropertyValueFactory<Usuario, String>("ficheiro"));
    	columnLigacao.setCellValueFactory(new PropertyValueFactory<Usuario, String>("ligacao"));

    	columnMensagem.setCellValueFactory(new PropertyValueFactory<Usuario, String>("mensagem"));
    	colunmTipoNumero.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipoNumero"));
    	idTabela.setItems(listContatos);
    	
    }

	public void pegarDados() {
		EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
	
		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				usuarioLogado.getContato();
				listContatos.addAll(usuarioLogado.getContato());
				System.out.println(listContatos);
			}
		}
		em.close();
	}

}
