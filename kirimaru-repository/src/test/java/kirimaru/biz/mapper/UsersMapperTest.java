package kirimaru.biz.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.biz.mapper.dto.UserDto;
import kirimaru.biz.mapper.helper.CommonSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UsersMapperTest extends CommonSetup {

  @Autowired
  UsersMapper mapper;

  @Test
  void test_01() {
    UserDto entity = UserDto.builder()
        .userId("1000")
        .name("きり丸")
        .build();

    mapper.insert(entity);

    assertThat(
        mapper.findByPrimaryKey("1000")
    ).isEqualTo(entity);
  }
}