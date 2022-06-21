package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HelloController {

    public TextField nick;
    @FXML private Button loadImageBtn;
    public static BufferedImage originalImage;
    @FXML private ImageView originalImageView;
    private File selectedFile;

    @FXML protected void onLoadImageBtnClick()
    {
        Stage thisStage = (Stage) loadImageBtn.getScene().getWindow();
        originalImage = FileHandler.LoadImage(thisStage);
        originalImageView.setImage(FileHandler.convertToFxImage(originalImage));
    }

    @FXML
    protected void onNextBtnClick() throws IOException {
        if(Objects.equals(nick.getText(), "admin") && originalImage!=null){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("configuration.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("KONFIGURACJA");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nieprawidłowy nick lub nie wgrano odnisku palca", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void onClickImageBtnClick(MouseEvent mouseEvent) {
        final FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        selectedFile = fc.showOpenDialog(originalImageView.getScene().getWindow());
        Image img = new Image(selectedFile.getPath());
        originalImageView.setImage(img);
        originalImageView.setFitWidth(200);
        originalImageView.setFitHeight(150);
    }


}