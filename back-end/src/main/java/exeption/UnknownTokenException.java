package exeption;

public class UnknownTokenException extends RuntimeException {
    public UnknownTokenException(String message){
        super(message);
    }
}
