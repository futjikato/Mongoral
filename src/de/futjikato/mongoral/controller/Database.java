package de.futjikato.mongoral.controller;

import com.mongodb.DB;
import de.futjikato.mongoral.Main;
import de.futjikato.mongoral.MongoralException;
import de.futjikato.mongoral.config.Connection;
import de.futjikato.mongoral.database.Collection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author moritzspindelhirn
 * @todo Documentation
 * @category de.futjikato.mongoral.controller
 */
public class Database implements Initializable {

    public ListView<Collection> listCollections;

    private DB database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initConnection(Connection connection) {
        try {
            database = connection.connect();
        } catch (MongoralException e) {
            e.printStackTrace();
            return;
        }
        initCollectionList();
    }

    private void initCollectionList() {
        // create ObservableList
        ObservableList<Collection> observableList = FXCollections.observableArrayList();

        // todo add item factory ( adding some buttons or whatever )
        //listConnections.setCellFactory();

        // add click listener
        listCollections.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Collection>() {
            @Override
            public void changed(ObservableValue<? extends Collection> observableValue, Collection collection, Collection collection2) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        // set as item list for list view
        listCollections.setItems(observableList);

        // iterate over connections and add to observable list
        Collection[] collections = Collection.getAllFromDatabase(database);
        for(Collection collection : collections) {
            observableList.add(collection);
        }
    }
}
