package de.futjikato.mongoral.views;

import de.futjikato.mongoral.Main;
import de.futjikato.mongoral.MongoralException;
import de.futjikato.mongoral.com.ComMessage;
import de.futjikato.mongoral.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class LoginView implements Observer {

    private Scene loginScene;

    private LoginController controller;

    private static LoginView instance;

    private Stage stage;

    private LoginView() {
        // get controller
        controller = new LoginController();
        // load up scene
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
        loader.setController(controller);

        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginScene = new Scene(root);
    }

    public static LoginView getInstance() {
        if(instance == null) {
            instance = new LoginView();
        }

        return instance;
    }

    public void show() throws MongoralException {
        if(stage == null) {
            throw new MongoralException("No stage to show login view on.");
        }

        stage.setScene(loginScene);
    }

    @Override
    public void update(Observable o, Object arg) {
        // only handle ComMessage notifications
        if(!(arg instanceof ComMessage)) {
            return;
        }

        ComMessage message = (ComMessage) arg;
        // only handle interesting messages
        switch(message.getMessageType()) {
            // open login screen if connection was closed
            case CONNECTION_CLOSED:
                try {
                    show();
                } catch (MongoralException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void injectStage(Stage stage) {
        this.stage = stage;
    }
}
