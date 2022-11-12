package kirimaru.biz.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import kirimaru.biz.mapper.dto.CompanyDepartmentDto;
import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.biz.mapper.dto.DepartmentDto;
import kirimaru.biz.mapper.helper.CommonSetup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CompanyMapperTest extends CommonSetup {

  @Autowired
  CompanyMapper mapper;

  @Autowired
  DepartmentMapper departmentMapper;

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

  @Nested
  class LazyLoad {

    @Test
    void test_01() {
      DepartmentDto departmentDto = DepartmentDto.builder()
          .departmentId("100")
          .name("プロダクト部")
          .build();

      departmentMapper.insert(departmentDto);

      CompanyDto entity = CompanyDto.builder()
          .companyId("1")
          .name("親企業")
          .departmentList(List.of(departmentDto))
          .build();

      mapper.insert(entity);

      CompanyDepartmentDto companyDepartmentDto = CompanyDepartmentDto.builder()
          .companyId(entity.getCompanyId())
          .departmentId(departmentDto.getDepartmentId())
          .build();
      insertCompanyDepartment(companyDepartmentDto);

      // WHEN & THEN
      CompanyDto actual = mapper.findByPrimaryKey("1");
      actual.getDepartmentList();
      // TODO: lazy_load???

      assertThat(
          actual
      ).isEqualTo(entity);
    }
  }
}