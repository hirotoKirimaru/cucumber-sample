package kirimaru.biz.domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RunnableTests {

  @TestInstance(Lifecycle.PER_CLASS)
  @Nested
  class Nest {

    @MethodSource(value = "testSource")
    @ParameterizedTest(name = "ログインユーザのロールが{1}")
    public void test(Runnable runnable, String role) {
      runnable.run();
    }


    public Stream<Arguments> testSource() {
      Runnable systemAdmin = () -> {
        systemAdminRoleLogin();
        afterLogin();
      };

      Runnable userRole = () -> {
        userRoleLogin();
        afterLogin();
      };

      return Stream.of(
          Arguments.of(systemAdmin, "システム管理者"),
          Arguments.of(userRole, "ユーザー")
      );
    }
  }

  public void systemAdminRoleLogin() {
    System.out.println("SystemAdminRoleでログイン");
  }

  public void userRoleLogin() {
    System.out.println("userRoleでログイン");
  }

  public void afterLogin() {
    System.out.println("AfterLogin");
  }
}
