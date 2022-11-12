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

//  static int port = 6379;
  static {
//    int port = 49154;
    // latestだと接続できない？？？
    // alpineじゃないと接続できない？？？
//    GenericContainer<?>
    redis = new GenericContainer<>(DockerImageName.parse("redis:latest"))
//    redis = new GenericContainer<>(DockerImageName.parse("redis:7.0.5-alpine"))
//        .withExposedPorts(6379, 16379, 26379, 36379)
        .withExposedPorts(6379)
    ;
    // 結局、使えるポートかが分からないから、デフォルトの6379以外も開けておく
    // GitHub Actionsだと6379ポートは使ってる？


//    log.error("*************");
//    log.error(redis.toString());
//    System.out.println("****************");
//    System.out.println(redis);
    redis.start();
//    System.out.println("******************");
//    log.error("***********");
    System.setProperty("spring.redis.host", redis.getHost());
//    System.setProperty("spring.redis.port", redis.getFirstMappedPort().toString());
    System.setProperty("spring.redis.port", redis.getMappedPort(49154).toString());
  }

  // TODO: こっちじゃうまく接続できない
//  @DynamicPropertySource
//  static void setup(DynamicPropertyRegistry registry) {
//    registry.add("spring.redis.host", redis::getHost);
//    registry.add("spring.redis.port", () -> redis.getMappedPort(port).toString());
    // NOTE:
//    registry.add("spring.redis.port", () -> redis.getFirstMappedPort().toString());
//  }

  @Test
  void contextLoads() {
  }
}
