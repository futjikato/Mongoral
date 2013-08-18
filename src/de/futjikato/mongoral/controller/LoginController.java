package de.futjikato.mongoral.controller;

import de.futjikato.mongoral.Main;
import de.futjikato.mongoral.config.Config;
import de.futjikato.mongoral.config.Connection;
import de.futjikato.mongoral.nodes.ConnectionForm;
import de.futjikato.mongoral.nodes.ConnectionList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author moritzspindelhirn
 * @todo Documentation
 * @category de.futjikato.mongoral.controller
 */
public class LoginController implements Initializable {

    @FXML public ConnectionList listConnections;

    @FXML public ConnectionForm formConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // focus title input after rendering and stuff
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                formConnection.titleInput.requestFocus();
            }
        });
    }
}
