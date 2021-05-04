package br.edu.ifsc.proj2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import br.edu.ifsc.proj2.database.Conn;
import br.edu.ifsc.proj2.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ChamadaConfigController implements Initializable {
	
	public ObservableList<String> listSom = FXCollections.observableArrayList("Silencioso","Acorn","Cystal","Life's Good","Maple");

    @FXML
    private JFXComboBox<String> cbSomNotificacao;

    @FXML
    private JFXCheckBox cbSomChamada;

    @FXML
    private JFXButton btSalvarAlteracoes;

    @FXML
    void btSalvarAteracoesClicado(ActionEvent event) {
    	EntityManager em = Conn.getEntityManager();
		List<Usuario> users = em.createQuery("select user from Usuario as user", Usuario.class).getResultList();
		
		em.getTransaction().begin();
		
		for (Usuario usuarioLogado : users) {
			if (usuarioLogado.getNumero().contentEquals(LoginController.getUsuarioLogado().getNumero())) {
				usuarioLogado.setSomChamada(cbSomNotificacao.getValue());
				
				if(cbSomChamada.isSelected()) {
					usuarioLogado.setReduzDados(true);
				}
				else {
					usuarioLogado.setReduzDados(false);
				}

				em.getTransaction().commit();
				em.close();
			}
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbSomNotificacao.setItems(listSom);
		
	}

}