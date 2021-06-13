package kirimaru;

import kirimaru.biz.mapper.helper.MapperUnitTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MapperUnitTestConfig.class)
public class RepositoryApplication {
  @Test
  void contextLoads() {
  }
}
