package kirimaru.biz.domain;

import org.springframework.util.Assert;

public class AssertPractice {

  public static void notNullValidate(Object obj) {
    Assert.state(obj != null, "param is null");
  }
}
