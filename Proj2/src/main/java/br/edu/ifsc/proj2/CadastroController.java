package br.edu.ifsc.proj2;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroController {
	
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPais;

    @FXML
    private JFXButton btCadastrar;

    @FXML
    private JFXButton btVoltar;

    @FXML
    private TextField txtNumero;

    @FXML
    private JFXButton btAddImagem;

    public void clique(ActionEvent e) throws IOException {
		Button btClicado = (Button) e.getSource();
		if (btClicado == btVoltar) {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			stage.setScene(new Scene(root));
			stage.show();
			Stage stage2 = (Stage) btVoltar.getScene().getWindow();
			stage2.close();
		}
		if (btClicado == btCadastrar) {
			verificaCadastro();
		}
    }
    
    public void verificaCadastro() {
    	if(txtNome.getText().contentEquals("") && txtNumero.getText().contentEquals("") && txtPais.getText().contentEquals("")) {
    		Alert errorALert = new Alert(AlertType.ERROR);
			errorALert.setContentText("Cadastro inválido, não podem ter campos vazios");
			errorALert.showAndWait();
    		
    	}else {
    		EntityManager em = Conn.getEntityManager();
    		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();

    		for (Usuario u : users) {
    			if (txtNumero.getText().contentEquals(u.getNumero())) {
    	    		Alert errorALert = new Alert(AlertType.ERROR);
    				errorALert.setContentText("Número ja cadastrado");
    				errorALert.showAndWait();
    				em.close();
    				return;
    			}
    		}
    		
    		Usuario novoUsuario = new Usuario(txtNome.getText(), txtNumero.getText(), txtPais.getText());
    		
    		
	    		em.getTransaction().begin();
	    		em.persist(novoUsuario);
	    		em.getTransaction().commit();
	    		em.close();
	    		
	    		System.out.println(novoUsuario.getNome());
	    		System.out.println(novoUsuario.getNumero());
	    		System.out.println(novoUsuario.getPais());
	    		
	    		Alert errorALert = new Alert(AlertType.INFORMATION);
				errorALert.setContentText("Cadastro realizado");
				errorALert.showAndWait();
    	}
    }

}