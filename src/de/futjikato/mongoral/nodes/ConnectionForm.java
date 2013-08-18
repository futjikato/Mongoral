package de.futjikato.mongoral.nodes;

import de.futjikato.mongoral.Main;
import de.futjikato.mongoral.com.Com;
import de.futjikato.mongoral.com.ComMessage;
import de.futjikato.mongoral.config.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionForm extends GridPane implements Observer {

    private ConnectionList connectionList;

    @FXML public TextField titleInput;

    @FXML public TextField hostInput;

    @FXML public TextField portInput;

    @FXML public TextField usernameInput;

    @FXML public TextField passwordInput;

    @FXML public TextField databaseInput;

    public ConnectionForm() {
        // init form
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ConnectionForm.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // subscribe to com
        Com.getIsntance().addObserver(this);
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
            case CONNECTION_CHANGED:
                Connection connection = (Connection) message.getParameter();
                titleInput.setText(connection.getName());
                hostInput.setText(connection.getHost());
                portInput.setText(String.valueOf(connection.getPort()));
                usernameInput.setText(connection.getUsername());
                passwordInput.setText(connection.getPassword());
                databaseInput.setText(connection.getDatabase());
                break;
        }
    }
}
