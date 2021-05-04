package br.edu.ifsc.proj2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class DadosConfigController implements Initializable{
	
	public ObservableList<String> listArquivos = FXCollections.observableArrayList("Fotos", "Áudio", "Vídeos","Documentos");

    @FXML
    private JFXComboBox<String> cbDadosMoveis;

    @FXML
    private JFXComboBox<String> cbDadosWifi;

    @FXML
    private JFXComboBox<String> cbDadosRoaming;

    @FXML
    private JFXButton btSalvarAlteracoes;

    @FXML
    void btSalvarAteracoesClicado(ActionEvent event) {
    	EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		
		em.getTransaction().begin();
		
		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				usuarioLogado.setBackupDadosMoveis(cbDadosMoveis.getValue());
				usuarioLogado.setBackupRede(cbDadosWifi.getValue());
				usuarioLogado.setBackupRoaming(cbDadosRoaming.getValue());
				
				

				em.getTransaction().commit();
				em.close();
			}
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addComboBox();
		
	}
	
	void addComboBox() {
		cbDadosMoveis.setItems(listArquivos);
		cbDadosRoaming.setItems(listArquivos);
		cbDadosWifi.setItems(listArquivos);
	}

}