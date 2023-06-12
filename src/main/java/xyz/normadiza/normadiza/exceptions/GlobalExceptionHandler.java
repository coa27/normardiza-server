package xyz.normadiza.normadiza.exceptions;

import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import xyz.normadiza.normadiza.exceptions.customs.EmailNoValidoException;
import xyz.normadiza.normadiza.exceptions.customs.EntidadNoEncontradaException;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /***
     * Manejo para exception con los metodos HTTP erroneas.
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Problemas con el metodo de la request", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Acceso no autorizado", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EntidadNoEncontradaException.class)
    protected ResponseEntity<Object> handleEntidadNoEncontradaException(EntidadNoEncontradaException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Problemas al interactuar con la base de datos", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Problemas al autenticar usuario", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailNoValidoException.class)
    protected ResponseEntity<Object> handleEmailNoValidoException(EmailNoValidoException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Problemas al registro de usuario", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JWTCreationException.class)
    protected ResponseEntity<Object> handleJWTCreationException(JWTCreationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Problemas al crear el token", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
