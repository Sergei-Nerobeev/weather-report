package hu.nero.weather_report.advice;

import hu.nero.weather_report.exception.PlaceNotFoundException;
import hu.nero.weather_report.model.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(PlaceNotFoundException.class)
  public ResponseEntity<ErrorModel> handlePlaceNotFoundException(RuntimeException exception) {
    ErrorModel errorModel = new ErrorModel(exception.getMessage(), java.time.LocalDateTime.now());
    return ResponseEntity
        .badRequest()
        .body(errorModel);
  }
}
