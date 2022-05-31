package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;

public class HelloController {
    public TextField nick;
    public RadioButton global;
    public ToggleGroup option;
    public ToggleGroup option2;
    public ToggleGroup option3;
    public RadioButton otsu;
    public RadioButton niblack;
    public RadioButton sauvola;
    public RadioButton phansalkar;
    public RadioButton bernsen;
    public RadioButton luwu;
    public RadioButton kapura;
    public RadioButton kmm;
    public RadioButton k3m;
    public RadioButton zhang;
    public CheckBox crossing;
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