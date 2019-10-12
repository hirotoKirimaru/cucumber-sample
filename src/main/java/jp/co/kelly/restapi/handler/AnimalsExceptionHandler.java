package jp.co.kelly.restapi.handler;

import jp.co.kelly.restapi.AnimalsRestController;
import jp.co.kelly.restapi.GamesRestController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(assignableTypes = {AnimalsRestController.class, GamesRestController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AnimalsExceptionHandler{

  @ExceptionHandler(BindException.class)
  public ResponseEntity<Object> handleRundtimeException(BindException ex, WebRequest request) {
    return new ResponseEntity<>("tokubetu",HttpStatus.BAD_REQUEST);
  }
}
