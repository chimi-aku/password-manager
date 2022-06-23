package com.example.passwordmanager;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerController {
    public TableColumn<String[],String> url;
    public TableColumn<String[],String> pass;
    public Button saveToFile;
    private int id;
    void initData2(int id) {
        this.id = id;
    }
    @FXML
    private TableView<String[]> list;

    public ManagerController() {


    }
    @FXML
    void initialize(){

    }
    public  void PrepTable() throws SQLException {
        try(var conn = DManager.connect()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select public.\"ListOfPasswords\".\"Link\"" +
                            ", public.\"ListOfPasswords\".\"Pass\" " +
                            "from public.\"ListOfPasswords\" " +
                            "where public.\"ListOfPasswords\".\"IdUser\" ="+id);
            List<String[]> allRows = new ArrayList<>();

            while(rs.next()){
                String[] row = new String[2];
                for(int i = 1;i<=2;i++){
                    row[i-1]=rs.getString(i);
                }
                allRows.add(row);
            }

            url.setCellValueFactory((p)->{
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length>0 ? x[0] : "<no name>");
            });

            pass.setCellValueFactory((p)->{
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length>1 ? x[1] : "<no value>");
            });
            list.getItems().addAll(allRows);
        }

    }
    public void AddBtn(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Dodaj");
        dialog.setHeaderText("Podaj link i hasło");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField urlField = new TextField("Link");
        TextField passField = new TextField("Hasło");
        dialogPane.setContent(new VBox(8, urlField, passField));
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(urlField.getText(),
                        passField.getText());
            }
            return null;
        });


        new Thread(() -> {
            try(var conn = DManager.connect()){
                Statement statement = conn.createStatement();
                statement.executeUpdate("insert into public.\"ListOfPasswords\"(Link,Pass,IdUser) values()");
            } catch (SQLException ignored) {}

        }).start();

        list.getItems().add(list.getItems().size(),new String[]{"xd","hehe"});
        list.refresh();
    }

    public void DeleteBtn(ActionEvent actionEvent) {
        if(list.getSelectionModel().getSelectedItem() != null){
            list.getItems().remove(list.getSelectionModel().getSelectedItem());
            list.refresh();
        }

    }

    public void EditBtn(ActionEvent actionEvent) {

    }

    public void ShowTable(ActionEvent actionEvent) throws SQLException {
        PrepTable();
    }

    private static class Results {
        String url;
        String pass;

        public Results(String url, String pass){
            this.url = url;
            this.pass = pass;
        }
    }

}

