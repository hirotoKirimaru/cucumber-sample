package com.example.demo.domain.interceptor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DomainInterceptor {

  @Before(
      "execution(* com.example.demo.domain.*.*(..)))"
  )
  void before() {
    System.out.println("*** START ***");
  }

  @After(
      "execution(* com.example.demo.domain.*.*(..)))"
  )
  void after() {
    System.out.println("*** END ***");
  }
}
