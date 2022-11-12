package kirimaru.biz.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import kirimaru.biz.mapper.dto.DepartmentDto;
import kirimaru.biz.mapper.dto.UserDto;
import kirimaru.biz.mapper.helper.CommonSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DepartmentMapperTest extends CommonSetup {

  @Autowired
  DepartmentMapper mapper;

  @Test
  void test_01() {
    DepartmentDto entity = DepartmentDto.builder()
        .departmentId("100")
        .name("コンシューマ部開発課")
        .build();

    mapper.insert(entity);

    assertThat(
        mapper.findByPrimaryKey("100")
    ).isEqualTo(entity);
  }
}