package kirimaru.biz.domain.conflict.register;

import kirimaru.biz.service.conflict.register.ConflictComponent;
import kirimaru.biz.service.conflict.register.ConflictService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConflictComponentTests {
  @Autowired
  @Qualifier("kirimaru.biz.domain.conflict.register.ConflictService")
  ConflictService conflictService;

  ConflictComponent conflictComponent;

  @BeforeEach
  void setUp() {
    conflictComponent = new ConflictComponent(conflictService);
  }

  @Test
  void test_01() {

  }
}
