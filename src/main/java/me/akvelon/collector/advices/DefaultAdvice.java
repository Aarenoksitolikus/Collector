package me.akvelon.collector.advices;

import me.akvelon.collector.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(RuntimeException exception) {
        return ResponseEntity.badRequest()
                .body(ExceptionDto.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .build());
    }
}
