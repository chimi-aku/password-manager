package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class ConfigurationController {

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
    public RadioButton medianFilter;
    public RadioButton kuwaharaFilter;
    public RadioButton mdbutmfFilter;
    public RadioButton medianmedianFilter;
    public ToggleGroup opt1;
    public ToggleGroup opt2;
    public ToggleGroup opt3;
    public CheckBox crossing1;

    @FXML
    public void onLogInButtonClick(MouseEvent mouseEvent) throws IOException {
        if(medianFilter.isSelected())
        {

        }
        else if(kuwaharaFilter.isSelected())
        {

        }
        else if(mdbutmfFilter.isSelected())
        {

        }
        else
        {

        }

        if(kmm.isSelected())
        {

        }
        else if(k3m.isSelected())
        {

        }
        else
        {

        }

        if(global.isSelected())
        {

        }
        else if(otsu.isSelected())
        {

        }
        else if(niblack.isSelected())
        {

        }
        else if(bernsen.isSelected())
        {

        }
        else if(sauvola.isSelected())
        {

        }
        else if(phansalkar.isSelected())
        {

        }
        else if(luwu.isSelected())
        {

        }
        else
        {

        }

        if(crossing1.isSelected())
        {

        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manager.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Twoje hasła");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
