package kirimaru.biz.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import kirimaru.biz.mapper.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

@DataRedisTest
//@TestExecutionListeners(DisabledOnGitHubTestExecutionListener.class) // TODO: DIができなくなる。
//@DisabledOnGitHub
@DisabledOnOs(OS.LINUX)
class UserRedisMapperTests {
  @Autowired
  UserRedisMapper mapper;

  @BeforeEach
  void setUp() {
    mapper.deleteAll();
  }

  @Test
  void test_01() {

    assertThat(
        mapper.findAll()
    ).isEmpty();
  }

  @Test
  void test_02() {
    var USER1 = UserDto.builder().id("1").name("1").build();
    var USER2 = UserDto.builder().id("2").name("2").build();

    mapper.saveAll(List.of(USER1, USER2));

    assertThat(
        mapper.findAll()
    ).isEqualTo(List.of(USER1, USER2));
  }
}