package br.edu.ifsc.proj2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LigacaoController implements Initializable{

    @FXML
    private Label txtNomeContato;

    @FXML
    private ImageView iconDesligar;

    @FXML
    void Desligar(MouseEvent event) {
		Stage stage2 = (Stage) txtNomeContato.getScene().getWindow();
		stage2.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNomeContato.setText(ChamadaController.getContatoLigacao());
		
	}

}
