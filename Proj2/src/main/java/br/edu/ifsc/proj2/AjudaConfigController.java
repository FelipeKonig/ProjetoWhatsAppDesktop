package br.edu.ifsc.proj2;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AjudaConfigController {

    @FXML
    private TextArea txtMensagem;

    @FXML
    private JFXButton btEnviar;

    @FXML
    private ImageView iconFile;

    @FXML
    private Label btTermos;

    @FXML
    void btEnviarFeedback(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Problema enviado");
		alert.showAndWait();
		txtMensagem.clear();
    }

    @FXML
    void mostrarTermos(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Termos.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stage.setScene(new Scene(root));
		stage.show();
    }

}
