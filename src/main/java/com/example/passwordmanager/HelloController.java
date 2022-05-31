package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;

public class HelloController {
    @FXML private Button loadImageBtn;
    private BufferedImage originalImage;
    private ImageView originalImageView;
    @FXML private RadioButton medianFilter;
    @FXML private RadioButton kuwaharaFilter;
    @FXML private RadioButton mdbutmfFilter;
    @FXML private RadioButton medianmedianFilter;

    @FXML protected void onLoadImageBtnClick()
    {
        Stage thisStage = (Stage) loadImageBtn.getScene().getWindow();
        originalImage = FileHandler.LoadImage(thisStage);
        originalImageView.setImage(FileHandler.convertToFxImage(originalImage));
    }

    @FXML protected void onLogInBtnClick()
    {
        if(medianFilter.isSelected()){

        }
        if(kuwaharaFilter.isSelected()){

        }
        if(mdbutmfFilter.isSelected()){

        }
        if(medianmedianFilter.isSelected()){

        }

    }
}