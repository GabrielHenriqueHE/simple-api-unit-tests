package io.github.gabrielhenriquehe.simpleapi.infrastructure.exceptions;

import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.out.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalRequestHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppResponse<Void>> handleResourceNotFoundException(Exception e) {

        AppResponse<Void> response = new AppResponse<>(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
