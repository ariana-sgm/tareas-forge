import java.lang.Exception;

class ExistentUsernameException extends Exception{
    private static final long serialVersionUID = 535185139L;

    public ExistentUsernameException (String message){
        super(message);
    }

}