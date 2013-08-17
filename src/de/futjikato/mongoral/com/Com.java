package de.futjikato.mongoral.com;

import de.futjikato.mongoral.MongoralException;
import de.futjikato.mongoral.config.Connection;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class Com extends Observable {

    private static Com instance = new Com();

    private Com() {}

    public static Com getIsntance() {
        return instance;
    }

    public void notifyActiveConnection(Connection connection) throws MongoralException {
        ComMessage message = ComMessage.createComMessage(ComMessageType.CONNECTION_CHANGED, connection);
        setChanged();
        notifyObservers(message);
    }
}
