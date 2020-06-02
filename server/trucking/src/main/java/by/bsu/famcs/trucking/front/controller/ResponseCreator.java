package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.FailedLoginException;

public class ResponseCreator {
    public interface ResponseFunction<T> {
        T apply() throws ResourceAccessDeniedException, UserNotFoundException, FailedLoginException;
    }
    public static <T> ResponseEntity<?> body(ResponseFunction<T> function) {
        try {
            return ResponseEntity.ok(function.apply());
        } catch (ResourceAccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (FailedLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
