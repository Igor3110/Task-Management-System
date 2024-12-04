package by.trofimov.time_management_system.controller.handler;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import jakarta.persistence.NoResultException;
import org.springframework.http.ResponseEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import by.trofimov.time_management_system.dto.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<BaseResponse> onConstraintNoResultException(NoResultException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new BaseResponse(e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<BaseResponse> onConstraintValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new BaseResponse(e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseResponse> onEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse(e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<BaseResponse> onEntityExistsException(EntityExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponse(e.getMessage(), LocalDateTime.now()));
    }

}
