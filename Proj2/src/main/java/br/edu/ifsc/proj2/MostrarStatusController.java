package br.edu.ifsc.proj2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MostrarStatusController implements Initializable {

	private static String nome, textoStatus;
	
    @FXML
    private TextArea txtStatus;

    @FXML
    private Label txtNome;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtNome.setText(nome);
		txtStatus.setText(textoStatus);
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		MostrarStatusController.nome = nome;
	}

	public static String getTextoStatus() {
		return textoStatus;
	}

	public static void setTextoStatus(String textoStatus) {
		MostrarStatusController.textoStatus = textoStatus;
	}

}
