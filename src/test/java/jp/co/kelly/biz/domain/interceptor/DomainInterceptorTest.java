package jp.co.kelly.biz.domain.interceptor;

import jp.co.kelly.biz.domain.OneHundredDoors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

class DomainInterceptorTest {

  @DisplayName("ExceptionHandlerが処理の開始と終了時に標準出力をすることの確認")
  @Test
  void test_01() {
    AspectJProxyFactory factory = new AspectJProxyFactory(OneHundredDoors.builder()
        .doors(new boolean[100])
        .build()
    );
    factory.addAspect(new DomainInterceptor()); //ここでAspectLogicクラスを適用
    OneHundredDoors proxy = factory.getProxy();

    proxy.execute();

  }

}
