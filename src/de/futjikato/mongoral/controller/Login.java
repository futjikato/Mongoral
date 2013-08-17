package de.futjikato.mongoral.controller;

import de.futjikato.mongoral.Main;
import de.futjikato.mongoral.config.Config;
import de.futjikato.mongoral.config.Connection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class Login implements Initializable {

    public TextField fieldTitle;

    public TextField fieldHost;

    public TextField fieldPort;

    public TextField fieldUsername;

    public TextField fieldPassword;

    public ListView<Connection> listConnections;

    public TextField fieldDatabase;

    public Button btnSave;

    public Button btnUpdate;

    public Button btnReset;

    public Button btnConnect;

    public Pane paneConnectionError;

    private Connection editing;

    public void saveNewConnection(ActionEvent event) {
        // create new object
        Connection newCon = new Connection();
        // fill with user input
        updateConnectionFromInputs(newCon);
        // check if connection could be established
        if(newCon.test()) {
            // add to config and write it to hd
            Config.getInstance().addConnection(newCon);
            Config.getInstance().write();
            // hide may visible error pane
            paneConnectionError.setVisible(false);
        } else {
            // show error pane in case connection failed
            paneConnectionError.setVisible(true);
        }
        // add to list
        listConnections.getItems().add(newCon);
    }

    public void connectToCurrentConnection(ActionEvent event) {
        if(editing instanceof Connection) {
            if(editing.test()) {
                paneConnectionError.setVisible(false);
                Main.setActiveConnection(editing);
                Main.switchToDatabaseView();
            } else {
                paneConnectionError.setVisible(true);
            }
        }
    }

    public void updateEditedConnection(ActionEvent event) {
        if(editing instanceof Connection) {
            Connection update = editing;
            // update with user input
            updateConnectionFromInputs(update);
            // check if connectionis valid
            if(update.test()) {
                // save config to file
                Config.getInstance().write();
                // hide may visible error pane
                paneConnectionError.setVisible(false);
            } else {
                // show error pane in case connection failed
                paneConnectionError.setVisible(true);
            }
            // update list
            int index = listConnections.getItems().indexOf(update);
            listConnections.getItems().remove(update);
            listConnections.getItems().add(index, update);
            listConnections.getSelectionModel().select(update);
        }
    }

    protected void updateConnectionFromInputs(Connection connection) {
        connection.setName(fieldTitle.getText());
        connection.setHost(fieldHost.getText());
        connection.setPort(Integer.valueOf(fieldPort.getText()));
        connection.setUsername(fieldUsername.getText());
        connection.setPassword(fieldPassword.getText());
        connection.setDatabase(fieldDatabase.getText());
    }

    public void resetEditedConnection(ActionEvent event) {
        editing = null;
        btnReset.setVisible(false);
        btnUpdate.setVisible(false);
        btnSave.setVisible(true);
        btnConnect.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initConnectionList();
    }

    private void initConnectionList() {
        // create ObservableList
        ObservableList<Connection> observableList = FXCollections.observableArrayList();

        // todo add item factory ( adding delete button to active item in list )
        //listConnections.setCellFactory();

        // add click listener
        listConnections.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Connection>() {
            @Override
            public void changed(ObservableValue<? extends Connection> observableValue, Connection oldCon, Connection newCon) {
                updateInputs(newCon);
            }
        });

        // set as item list for list view
        listConnections.setItems(observableList);

        // iterate over connections and add to observable list
        Connection[] connections = Config.getInstance().getConnections();
        for(Connection con : connections) {
            observableList.add(con);
        }
    }

    private void updateInputs(Connection connection) {
        fieldTitle.setText(connection.getName());
        fieldHost.setText(connection.getHost());
        fieldPort.setText(String.valueOf(connection.getPort()));
        fieldUsername.setText(connection.getUsername());
        fieldPassword.setText(connection.getPassword());
        fieldDatabase.setText(connection.getDatabase());

        editing = connection;

        btnSave.setVisible(false);
        btnUpdate.setVisible(true);
        btnReset.setVisible(true);
        btnConnect.setVisible(true);
    }
}
