package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.security.auth.login.FailedLoginException;
import java.util.logging.Logger;

public class ResponseCreator {

    private static final Logger LOGGER = Logger.getLogger(ResponseCreator.class.getName());

    public interface ResponseFunction<T> {
        T apply() throws ResourceAccessDeniedException, UserNotFoundException, FailedLoginException;
    }
    public interface ResponseFunctionNoReturn<T> {
        void apply() throws ResourceAccessDeniedException, UserNotFoundException, FailedLoginException;
    }
    public static <T> ResponseEntity<?> body(ResponseFunction<T> function) {
        try {
            return ResponseEntity.ok(function.apply());
        } catch (ResourceAccessDeniedException e) {
            LOGGER.warning(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            LOGGER.warning(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (FailedLoginException e) {
            LOGGER.warning(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static <T> ResponseEntity<?> emptyBody(ResponseFunctionNoReturn<T> function) {
        return body(() -> {
            function.apply();
            return "";
        });
    }
}
