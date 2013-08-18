package de.futjikato.mongoral.com;

import de.futjikato.mongoral.MongoralException;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 17.08.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class ComMessage implements ComMessageInterface {

    private Object parameter;

    private ComMessageType type;

    @Override
    public ComMessageType getMessageType() {
        return type;
    }

    @Override
    public Object getParameter() {
        return parameter;
    }

    public static ComMessage createComMessage(ComMessageType type, Object parameter) throws MongoralException {
        // check parameter class
        Class parameterClass = type.getParameterClass();
        if(parameterClass != null) {
            if(parameter.getClass() != parameterClass) {
                throw new MongoralException("Message parameter does not match with required class.");
            }
        }

        ComMessage message = new ComMessage();
        message.type = type;
        message.parameter = parameter;

        return message;
    }
}
