package kirimaru.biz.domain.interceptor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DomainInterceptor {

  @Before(
      "execution(* kirimaru.biz.domain.*.*(..)))"
  )
  void before() {
    System.out.println("*** START ***");
  }

  @After(
      "execution(* kirimaru.biz.domain.*.*(..)))"
  )
  void after() {
    System.out.println("*** END ***");
  }
}
