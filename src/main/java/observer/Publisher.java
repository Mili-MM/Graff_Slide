package observer;

import error_handler.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public interface Publisher {
    void sendNotification(ErrorMessage errorMessage);
}
