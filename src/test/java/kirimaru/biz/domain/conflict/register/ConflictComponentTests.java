package kirimaru.biz.domain.conflict.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
