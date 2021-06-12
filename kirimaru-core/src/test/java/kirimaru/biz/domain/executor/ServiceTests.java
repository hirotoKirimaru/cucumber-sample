package kirimaru.biz.domain.executor;

import org.junit.jupiter.api.Test;

class ServiceTests {

  @Test
  void test_01() {
    Service service = new Service();
    service.getInt();
  }
}
