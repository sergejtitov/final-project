package htp.exceptions;

public class EntityAleadyExists extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityAleadyExists(String message) {
        super(message);
    }

    public EntityAleadyExists(String message, Exception ex) {
        super(message, ex);
    }
}
