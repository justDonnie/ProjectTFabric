package tisobay.projecttiso.exceptions;

public class BadRequestException extends  RuntimeException{
    public BadRequestException(String s){
        super(s);
    }
}