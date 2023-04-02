package com.example.exeption;

public class SyntaxErrorException extends Exception{
    public SyntaxErrorException(String message){
        super(message);
    }
    public SyntaxErrorException(String message, int line){
        super("Line "+ line + ": " + message);
    }

    public SyntaxErrorException(){
        super();
    }
}
