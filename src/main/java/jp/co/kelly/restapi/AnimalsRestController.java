package jp.co.kelly.restapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/animals")
public class AnimalsRestController {

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
}
