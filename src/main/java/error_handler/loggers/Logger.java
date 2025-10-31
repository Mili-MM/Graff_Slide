package error_handler.loggers;

import error_handler.ErrorMessage;
import error_handler.observer.Subscriber;

public abstract class Logger implements Subscriber{

    public Logger(){

    }

    protected String formatErrorMessage(ErrorMessage message){
        return message.getFormatedMessage();
    }

}
