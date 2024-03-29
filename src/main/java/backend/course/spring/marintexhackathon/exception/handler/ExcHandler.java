package backend.course.spring.marintexhackathon.exception.handler;

import backend.course.spring.marintexhackathon.exception.BaseException;
import backend.course.spring.marintexhackathon.exception.NotFoundException;
import backend.course.spring.marintexhackathon.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ExceptionResponse UserAlreadyExistException(UserAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.FOUND, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse NotFoundException(NotFoundException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse registrationTokenExpiredException(BaseException e) {
        return new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED, e.getClass().getName(), e.getMessage());
    }

}
