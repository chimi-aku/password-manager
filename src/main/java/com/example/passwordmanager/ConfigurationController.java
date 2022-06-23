package com.example.passwordmanager;

import com.example.passwordmanager.binarization.*;
import com.example.passwordmanager.minutiae.ImageMinutiae;
import com.example.passwordmanager.minutiae.MinutiaeExtraction;
import com.example.passwordmanager.noise.MedianFilter;
import com.example.passwordmanager.HelloController;
import com.example.passwordmanager.skeletonization.K3M;
import com.example.passwordmanager.skeletonization.ZhangSuen;
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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.passwordmanager.HelloController.originalImage;

public class ConfigurationController {
    private int id;
    private int[] minution;
    void initData(int id) {
        this.id = id;
    }

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
        BufferedImage after_noise;
        BufferedImage after_binarization = null;
        int[][] after_binarization_array;
        int[][] after_skeletonization;
        BufferedImage after_crossing;

        after_noise = MedianFilter.median(originalImage, 3);

        if(global.isSelected())
        {
            after_binarization = GlobalThresholding.simpleBinarization(after_noise, 3);
        }
       /* else if(otsu.isSelected())
        {

        }*/
        else if(niblack.isSelected())
        {
            after_binarization = Niblack.NiblackBinarization(after_noise, 3, 4);
        }
        /*else if(bernsen.isSelected())
        {

        }*/
        else if(sauvola.isSelected())
        {
            after_binarization = Sauvola.SauvolaBinarization(after_noise, 3, 4);
        }
        else if(phansalkar.isSelected())
        {
            after_binarization = Phansalkar.PhansalkarBinarization(after_noise, 3, 4);
        }
        /*else if(luwu.isSelected())
        {

        }
        else
        {

        }*/

        if(k3m.isSelected())
        {
            after_binarization_array = Helpers.convertBinarizatedImgToArray2D(after_binarization);
            after_skeletonization = K3M.thinImage(after_binarization_array);
        }
        else
        {
            after_binarization_array = Helpers.convertBinarizatedImgToArray2D(after_binarization);
            after_skeletonization = ZhangSuen.thinImage(after_binarization_array);
        }

        if(crossing1.isSelected())
        {

        }
        try(var conn = DManager.connect()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select *"+
                            "from public.\"Users\" " +
                            "where public.\"Users\".\"Id\" ="+id);
            rs.next();
            rs.getInt(1);//id
            rs.getString(2);//login
        }
        catch (SQLException ignored) {}

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manager.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Twoje hasła");
        stage.setScene(new Scene(root1));
        ManagerController controller = fxmlLoader.getController();
        controller.initData2(id, minution);
        stage.show();
    }
}
