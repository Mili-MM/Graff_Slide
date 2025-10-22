package observer;

import error_handler.ErrorMessage;

public interface Subscriber {
    void update(ErrorMessage errorMessage);
}
