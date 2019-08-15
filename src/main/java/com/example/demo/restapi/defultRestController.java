package com.example.demo.restapi;

import com.example.demo.exception.AlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
public class defultRestController {

  @RequestMapping("/")
  public String hogeApi(@Validated Param param) throws Exception {
    if (Objects.equals(param.getPiyo(), "runtime")) {
      throw new AlreadyExistsException();
    }

    return "hogehoge";
  }

  @Data
  @AllArgsConstructor
  private static class Param {
    @NotNull
    private String piyo;
  }

//    @Component
//    private static class ParamValidater implements Validator {
//
//        @Override
//        public boolean supports(Class<?> clazz) {
//            return Param.class.isAssignableFrom(clazz); // (2)
//        }
//
//        @Override
//        public void validate(Object target, Errors errors) throws RuntimeException{
//            if (errors.hasFieldErrors()) { // (3)
//                return;
//            }
//
//            Param form = (Param) target;
//            String piyo = form.getPiyo();
//
//            if (Objects.equals(piyo, "runtime")) {
//                errors.rejectValue("piyo", "aiueo");
////                throw new RuntimeException();
//            }
//        }
//}


}
