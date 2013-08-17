package de.futjikato.mongoral.com;

import de.futjikato.mongoral.config.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
public enum ComMessageType {
    CONNECTION_CHANGED(Connection.class),
    CONNECTION_CLOSED(Connection.class);

    private Class parameterClass;

    ComMessageType(Class parameterClass) {
        this.parameterClass = parameterClass;
    }

    public Class getParameterClass() {
        return parameterClass;
    }
}
