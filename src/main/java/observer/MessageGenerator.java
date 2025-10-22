package observer;

import error_handler.ErrorMessage;
import error_handler.LoggerFactory;
import error_handler.loggers.ConsoleLogger;
import error_handler.loggers.FileLogger;
import error_handler.loggers.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher{

    private ArrayList<Subscriber> subscribers = new ArrayList<>();
    private LoggerFactory loggerFactory;
    private Logger fileLog;
    private Logger consoleLog;

    public MessageGenerator() {
        initialise();
    }

    private void initialise(){
        loggerFactory = new LoggerFactory();
        fileLog = loggerFactory.createLogger("filelogger");
        consoleLog = loggerFactory.createLogger("consolelogger");
        addSubsriber(fileLog);
        addSubsriber(consoleLog);
    }

    @Override
    public void sendNotification(ErrorMessage errorMessage) {
        if(!subscribers.isEmpty()){
            for(Subscriber s: subscribers){
                s.update(errorMessage);
            }
        }
    }

    public void addSubsriber(Subscriber s){
        if(!subscribers.contains(s)){
            subscribers.add(s);
        }
    }

}
