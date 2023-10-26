package evovital.uniquindio.edu.co.exceptions.handlers;


import evovital.uniquindio.edu.co.exceptions.exceptions.LoginValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class LoginHandler {

    @ExceptionHandler(LoginValidationException.class)
    public ResponseEntity<Map<String, String>> loginValidationException(LoginValidationException e){

        switch (e.getMessage()){

            case "El usuario no existe":
                return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()))
                        .body(Map.of("Error", e.getMessage()));



        }

        return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())).body(Map.of("Error", e.getMessage()));

    }
}
