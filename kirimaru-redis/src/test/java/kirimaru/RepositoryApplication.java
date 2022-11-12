package kirimaru;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RepositoryApplication {

  @Container
  private static final GenericContainer<?> redis;

  static {
    // latestだと接続できない？？？
    // alpineじゃないと接続できない？？？
//    GenericContainer<?>
      redis = new GenericContainer<>(DockerImageName.parse("redis:latest"))
//    redis = new GenericContainer<>(DockerImageName.parse("redis:7.0.5-alpine"))
        .withExposedPorts(49154);
      // TODO: GitHub Actionsでは49154????

//    log.error("*************");
//    log.error(redis.toString());
//    System.out.println("****************");
//    System.out.println(redis);
    redis.start();
//    System.out.println("******************");
//    log.error("***********");
    System.setProperty("spring.redis.host", redis.getHost());
    System.setProperty("spring.redis.port", redis.getMappedPort(49154).toString());
  }

//  @DynamicPropertySource
//  static void setup(DynamicPropertyRegistry registry) {
//  }

  @Test
  void contextLoads() {
  }
}
