package backend.course.spring.marintexhackathon.exceptions;

public class ArgumentException extends IllegalArgumentException{
    public ArgumentException(String msg){
        super(msg);
    }
}
