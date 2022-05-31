package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloController {

    @FXML private Button loadImageBtn;
    private BufferedImage originalImage;
    @FXML private ImageView originalImageView;

    @FXML protected void onLoadImageBtnClick()
    {
        Stage thisStage = (Stage) loadImageBtn.getScene().getWindow();
        originalImage = FileHandler.LoadImage(thisStage);
        originalImageView.setImage(FileHandler.convertToFxImage(originalImage));
    }

    @FXML protected void onNextBtnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("configuration.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("KONFIGURACJA");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}