package kirimaru.biz.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

@DataRedisTest
class UserRedisMapperTests {
  @Autowired
  UserRedisMapper mapper;

  @Test
  void test_01() {

    assertThat(
        mapper.findAll()
    ).isEmpty();
  }
}