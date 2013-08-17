package de.futjikato.mongoral.nodes;

import de.futjikato.mongoral.Main;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionForm extends GridPane {

    private ConnectionList connectionList;

    public ConnectionForm() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ConnectionForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConnectionList(ConnectionList connectionList) {
        this.connectionList = connectionList;
    }
}
