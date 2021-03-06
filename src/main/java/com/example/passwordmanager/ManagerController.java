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
import java.util.Optional;

public class ManagerController {
    public TableColumn<String[],String> url;
    public TableColumn<String[],String> pass;
    public Button saveToFile;
    private int id;
    private int[] minution;
    void initData2(int id, int[] minution) {
        this.id = id;
        this.minution = minution;
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
                rs.getString(1);

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
        dialog.setHeaderText("Podaj link i has??o");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField urlField = new TextField();
        urlField.setPromptText("Link");
        TextField passField = new TextField();
        passField.setPromptText("Has??o");

        dialogPane.setContent(new VBox(8, urlField, passField));

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(urlField.getText(),
                        passField.getText());
            }
            return null;
        });

        Optional<Results> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Results results) -> {

            list.getItems().add(list.getItems().size(),new String[]{results.url,results.pass});
            list.refresh();

            new Thread(() -> {
                try(var conn = DManager.connect()){
                    Statement statement = conn.createStatement();
                    statement.executeUpdate("insert into public.\"ListOfPasswords\"values(nextval('listseq'),'"+results.url+"','"+results.pass+"',"+id+")");
                } catch (SQLException e) {
                    System.out.println(e);
                }

            }).start();


        });


    }

    public void DeleteBtn(ActionEvent actionEvent) {


        if(list.getSelectionModel().getSelectedItem() != null){
            list.getItems().remove(list.getSelectionModel().getSelectedItem());
            list.refresh();
        }
        String[] tmp = list.getSelectionModel().getSelectedItem();
        new Thread(() -> {
            try(var conn = DManager.connect()){
                Statement statement = conn.createStatement();
                statement.executeUpdate("delete from public.\"ListOfPasswords\" lop where lop.\"Link\" = '"+ tmp[0]+ "' and lop.\"Pass\" ='"+tmp[1]+"'");
            } catch (SQLException e) {
                System.out.println(e);
            }

        }).start();


    }

    public void EditBtn(ActionEvent actionEvent) {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("Edytuj");
        dialog.setHeaderText("Podaj link i has??o");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        String[] toUp = list.getSelectionModel().getSelectedItem();
        TextField urlField = new TextField(toUp[0]);
        urlField.setPromptText("Link");
        TextField passField = new TextField(toUp[1]);
        passField.setPromptText("Has??o");

        dialogPane.setContent(new VBox(8, urlField, passField));

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(urlField.getText(),
                        passField.getText());
            }
            return null;
        });

        Optional<Results> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Results results) -> {

            String[] tmp = {results.url,results.pass};
            if(list.getSelectionModel().getSelectedItem() != null){

                list.getItems().add(list.getSelectionModel().getSelectedIndex(),tmp);
                list.getItems().remove(list.getSelectionModel().getSelectedIndex());
                list.refresh();
            }

            new Thread(() -> {
                try(var conn = DManager.connect()){
                    Statement statement = conn.createStatement();
                    statement.executeUpdate("update public.\"ListOfPasswords\" lop set \"Link\" = '"+tmp[0]+"',\"Pass\" = '"+ tmp[1]+"'  where lop.\"Link\" = '"+ toUp[0]+ "' and lop.\"Pass\" ='"+toUp[1]+"'");
                } catch (SQLException e) {
                    System.out.println(e);
                }

            }).start();


        });

    }

    public void ShowTable(ActionEvent actionEvent) throws SQLException {
        PrepTable();
    }

    public void SaveBtn(ActionEvent actionEvent) {
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

