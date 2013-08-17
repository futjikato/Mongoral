package de.futjikato.mongoral;

import de.futjikato.mongoral.config.Connection;
import de.futjikato.mongoral.controller.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static Stage stage;

    private static Parent loginFXML;

    private static Parent databaseFXML;

    private static Database dbController;

    private static Connection activeConnection;

    @Override
    public void start(Stage stage) throws Exception{
        Main.stage = stage;

        Main.loginFXML = FXMLLoader.load(getClass().getResource("login.fxml"));

        // init database view
        FXMLLoader loader = new FXMLLoader();

        dbController = (Database) loader.getController();

        stage.setTitle("Mongoral");
        switchToLoginView();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void switchToLoginView() {
        stage.setScene(new Scene(loginFXML));
    }

    public static void switchToDatabaseView() {
        Scene dbScene = new Scene(databaseFXML);
        stage.setScene(dbScene);
        dbController.initConnection(activeConnection);
    }

    public static void setActiveConnection(Connection connection) {
        Main.activeConnection = connection;
    }

    public static Connection getActiveConnection() {
        return activeConnection;
    }
}
