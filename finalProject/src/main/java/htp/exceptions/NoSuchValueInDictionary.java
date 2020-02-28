package htp.exceptions;

public class NoSuchValueInDictionary extends RuntimeException {
    public NoSuchValueInDictionary(String message){
        super(message);
    }

    public NoSuchValueInDictionary(String message, Exception ex){
        super(message, ex);
    }
}
