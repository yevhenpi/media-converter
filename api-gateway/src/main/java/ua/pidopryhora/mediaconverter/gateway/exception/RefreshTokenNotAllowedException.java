package ua.pidopryhora.mediaconverter.gateway.exception;


public class RefreshTokenNotAllowedException extends RuntimeException {
    public RefreshTokenNotAllowedException(String message) {
        super(message);
    }
}
