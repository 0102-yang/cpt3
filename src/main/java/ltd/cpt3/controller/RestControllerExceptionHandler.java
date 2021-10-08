package ltd.cpt3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yang
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class, BadCredentialsException.class, RuntimeException.class})
    public ResponseEntity<String> handleUserNotFound(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
