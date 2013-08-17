package de.futjikato.mongoral.com;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
public interface ComMessageInterface {
    public ComMessageType getMessageType();
    public Object getParameter();
}
