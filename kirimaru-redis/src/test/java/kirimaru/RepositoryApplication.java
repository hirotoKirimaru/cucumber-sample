package kirimaru;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootApplication
@Testcontainers
public class RepositoryApplication {

  @Container
  private static final GenericContainer<?> redis;

  static {
    redis = new GenericContainer<>(DockerImageName.parse("redis:latest"))
        .withExposedPorts(6379);
    redis.start();
  }

  @Test
  void contextLoads() {
  }
}
