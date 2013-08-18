package de.futjikato.mongoral.views;

import de.futjikato.mongoral.Main;
import de.futjikato.mongoral.com.ComMessage;
import de.futjikato.mongoral.controller.DatabaseController;
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
 * Date: 18.08.13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseView implements Observer {

    private DatabaseController controller;

    private Scene databaseScene;

    private Stage stage;

    public DatabaseView() {
        // get controller
        controller = new DatabaseController();
        // load up scene
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/DatabaseView.fxml"));
        loader.setController(controller);

        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseScene = new Scene(root);
    }

    public void injectStage(Stage stage) {
        this.stage = stage;
    }

    private void show() {

    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        // only handle ComMessage notifications
        if(!(arg instanceof ComMessage)) {
            return;
        }

        ComMessage message = (ComMessage) arg;

        switch(message.getMessageType()) {
            case CONNECTION_ESTABLISHED:
                break;
        }
    }
}
