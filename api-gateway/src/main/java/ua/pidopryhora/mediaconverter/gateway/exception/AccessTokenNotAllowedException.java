package ua.pidopryhora.mediaconverter.gateway.exception;

public class AccessTokenNotAllowedException extends RuntimeException {
    public AccessTokenNotAllowedException(String message) {
        super(message);
    }
}

