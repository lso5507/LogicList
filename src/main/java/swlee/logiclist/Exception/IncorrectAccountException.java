package swlee.logiclist.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectAccountException extends RuntimeException{
    public IncorrectAccountException(String message) {
        super(message);
        log.error("IncorrectAccountException:{}",message);
    }

    public IncorrectAccountException(String message, Throwable cause) {
        super(message, cause);
        log.error("IncorrectAccountException:{}",message,cause);

    }

    public IncorrectAccountException(Throwable cause) {
        super(cause);
        log.error("IncorrectAccountException ",cause);

    }
}
