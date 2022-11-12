package kirimaru.biz.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.biz.mapper.helper.CommonSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CompanyMapperTest extends CommonSetup {

  @Autowired
  CompanyMapper mapper;

  @Test
  void test_01() {
    CompanyDto entity = CompanyDto.builder()
        .companyId("1")
        .name("親企業")
        .build();

    mapper.insert(entity);

    assertThat(
        mapper.findByPrimaryKey("1")
    ).isEqualTo(entity);
  }
}