package de.futjikato.mongoral.nodes;

import de.futjikato.mongoral.config.Config;
import de.futjikato.mongoral.config.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionList extends ListView<Connection> {

    public ConnectionList() {
        ObservableList<Connection> connectionList = FXCollections.observableArrayList();
        Connection[] connections = Config.getInstance().getConnections();

        for(Connection connection : connections) {
            connectionList.add(connection);
        }

        setItems(connectionList);
    }
}
