import java.lang.Exception;

class InvalidLogInException extends Exception{
    private static final long serialVersionUID = 53589185139L;

    public InvalidLogInException (String message){
        super(message);
    }

}