package pl.insert.exception;

public class NotMatchingPassword extends Throwable {
    public NotMatchingPassword(final String message){
        super(message);
    }
}
