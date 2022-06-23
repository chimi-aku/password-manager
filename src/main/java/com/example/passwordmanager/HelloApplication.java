package com.example.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.passwordmanager.DManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Password Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        /*
        DManager dmanager = new DManager();
        Connection conn = dmanager.connect();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from public.\"Users\" where public.\"Users\".\"Id\" = 2");
        System.out.println(rs.next()?"y":"n");*/

        launch();
    }

}