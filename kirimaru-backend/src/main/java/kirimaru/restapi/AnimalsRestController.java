package kirimaru.restapi;

import kirimaru.biz.domain.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/animals")
public class AnimalsRestController {

  @GetMapping(value = "")
  public String hogeApi(@Valid Param param)  {
    return "hogehoge";
  }

  @PostMapping(value = "")
  public String postApi(@Valid Animal param)  {
    return "hogehoge";
  }

  @PatchMapping(value = "")
  public String update(@Valid Animal param)  {
    return "patch";
  }

  @Data
  @AllArgsConstructor
  private static class Param {
    @NotNull
    @Size(min = 1, max = 10)
    private String piyo;
  }

  @PostConstruct
  public void validate(){
    System.out.println("かきくけこ");
  }
}
