public class SyntaxErrorException extends Exception{
    public SyntaxErrorException(String message){
        super(message);
    }

    public SyntaxErrorException(){
        super();
    }
}
