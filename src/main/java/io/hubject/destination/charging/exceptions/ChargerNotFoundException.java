package io.hubject.destination.charging.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChargerNotFoundException extends RuntimeException {
    public ChargerNotFoundException() {
    }

    public ChargerNotFoundException(String message) {
        super(message);
    }

    public ChargerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChargerNotFoundException(Throwable cause) {
        super(cause);
    }

    public ChargerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
