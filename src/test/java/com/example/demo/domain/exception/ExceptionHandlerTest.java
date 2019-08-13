package com.example.demo.domain.exception;

import com.example.demo.domain.OneHundredDoors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerTest {

  @DisplayName("ExceptionHandlerが処理の開始と終了時に標準出力をすることの確認")
  @Test
  void test_01() {
    AspectJProxyFactory factory = new AspectJProxyFactory(OneHundredDoors.builder()
        .doors(new boolean[100])
        .build()
    );
    factory.addAspect(new ExceptionHandler()); //ここでAspectLogicクラスを適用
    OneHundredDoors proxy = factory.getProxy();

    proxy.execute();

  }

}