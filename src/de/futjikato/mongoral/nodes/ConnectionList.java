package de.futjikato.mongoral.nodes;

import de.futjikato.mongoral.MongoralException;
import de.futjikato.mongoral.com.Com;
import de.futjikato.mongoral.config.Config;
import de.futjikato.mongoral.config.Connection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionList extends ListView<Connection> implements Observer {

    public ConnectionList() {
        // fill list
        ObservableList<Connection> connectionList = FXCollections.observableArrayList();
        Connection[] connections = Config.getInstance().getConnections();

        for(Connection connection : connections) {
            connectionList.add(connection);
        }

        setItems(connectionList);

        // subscribe to com
        Com.getIsntance().addObserver(this);

        // add change listener
        initItemChangeListener();
    }

    private void initItemChangeListener() {
        getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Connection>() {
            @Override
            public void changed(ObservableValue<? extends Connection> observableValue, Connection oldConnection, Connection newConnection) {
                try {
                    Com.getIsntance().notifyActiveConnection(newConnection);
                } catch (MongoralException e) {
                    // todo show user error somewhere
                    // todo log error somewhere
                    e.printStackTrace();
                }
            }
        });
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
