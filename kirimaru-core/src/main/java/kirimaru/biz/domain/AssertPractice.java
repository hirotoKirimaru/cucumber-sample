package kirimaru.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AssertPractice {

  public static void notNullValidate(Object obj) {
    Assert.state(obj != null, "param is null");
  }
}
