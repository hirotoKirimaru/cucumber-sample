package com.example.demo.domain.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandler {

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
