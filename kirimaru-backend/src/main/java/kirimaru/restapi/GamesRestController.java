package kirimaru.restapi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@Validated
@RestController
@RequestMapping("/games")
public class GamesRestController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String hogeApi(@Valid Param param)  {
    return "hogehoge";
  }

  @Data
  @AllArgsConstructor
  private static class Param {
    @NotNull
    @Size(min = 1, max = 10)
    private String piyo;
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<Object> handleRundtimeException(BindException ex, WebRequest request) {
    return new ResponseEntity<>("gameException", HttpStatus.BAD_REQUEST);
  }
}
