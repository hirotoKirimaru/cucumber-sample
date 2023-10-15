package kirimaru;

import kirimaru.biz.mapper.helper.MapperUnitTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootApplication
@Import(MapperUnitTestConfig.class)
@Testcontainers
public class RepositoryApplication {

  // TODO: https://www.testcontainers.org/test_framework_integration/manual_lifecycle_control/
  // おそらく動いていないが、複数テストで一つのコンテナを使いまわす方法を素振りする
  @Container
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres"))
      .withUsername("devuser")
      .withPassword("devuser")
      .withDatabaseName("devdb");

  @DynamicPropertySource
  static void setup(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl); // コンテナで起動中のPostgresへ接続するためのJDBC URLをプロパティへ設定
  }

  @Test
  void contextLoads() {
  }
}
