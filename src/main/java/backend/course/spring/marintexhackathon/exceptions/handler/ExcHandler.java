package backend.course.spring.marintexhackathon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse illegalArgumentException(IllegalArgumentException e){
        return new ExceptionResponse(HttpStatus.CONFLICT, e.getClass().getName(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleException(Exception ex) {
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getClass().getName(),ex.getMessage());
    }


}
