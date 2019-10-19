import java.lang.Exception;

class DifferentPasswordException extends Exception{
    private static final long serialVersionUID = 53556185139L;

    public DifferentPasswordException (String message){
        super(message);
    }

}