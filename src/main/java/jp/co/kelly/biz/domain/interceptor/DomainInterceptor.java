package jp.co.kelly.biz.domain.interceptor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DomainInterceptor {

  @Before(
      "execution(* jp.co.kelly.biz.domain.*.*(..)))"
  )
  void before() {
    System.out.println("*** START ***");
  }

  @After(
      "execution(* jp.co.kelly.biz.domain.*.*(..)))"
  )
  void after() {
    System.out.println("*** END ***");
  }
}
