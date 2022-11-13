package kirimaru.biz.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import kirimaru.biz.mapper.dto.CompanyDepartmentDto;
import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.biz.mapper.dto.DepartmentDto;
import kirimaru.biz.mapper.dto.DepartmentUserDto;
import kirimaru.biz.mapper.dto.UserDto;
import kirimaru.biz.mapper.helper.CommonSetup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CompanyMapperTest extends CommonSetup {

  @Autowired
  CompanyMapper mapper;

  @Autowired
  DepartmentMapper departmentMapper;

  @Autowired
  UsersMapper usersMapper;

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
      UserDto userDto = UserDto.builder()
          .userId("10000")
          .name("きり丸")
          .build();
      usersMapper.insert(userDto);

      DepartmentDto departmentDto = DepartmentDto.builder()
          .departmentId("100")
          .name("プロダクト部")
          .userList(List.of(userDto))
          .build();

      departmentMapper.insert(departmentDto);

      CompanyDto entity = CompanyDto.builder()
          .companyId("1")
          .name("親企業")
          .departmentList(List.of(departmentDto))
          .build();

      mapper.insert(entity);

      // 関連テーブル
      DepartmentUserDto departmentUserDto = DepartmentUserDto.builder()
          .departmentId(departmentDto.getDepartmentId())
          .userId(userDto.getUserId())
          .build();
      insert(departmentUserDto);

      CompanyDepartmentDto companyDepartmentDto = CompanyDepartmentDto.builder()
          .companyId(entity.getCompanyId())
          .departmentId(departmentDto.getDepartmentId())
          .build();
      insertCompanyDepartment(companyDepartmentDto);

      // WHEN & THEN
      CompanyDto actual = mapper.findByPrimaryKey("1");
      assertThat(
          actual
      ).isEqualTo(entity);
    }

    @DisplayName("""
        [GIVEN]
        ・1企業
        ・1企業に3組織紐づく
        ・組織Aにユーザ1, ユーザ2
        ・組織Bにユーザ3
        ・組織Cにユーザは紐づかない
        [WHEN]
        ・企業Mapperを取得する
        [THEN]
        ・全部取得できること 
        """
    )
    @Test
    void test_02() {
      UserDto userDto = UserDto.builder()
          .userId("10000")
          .name("きり丸1")
          .build();
      UserDto userDto2 = UserDto.builder()
          .userId("10001")
          .name("きり丸2")
          .build();
      UserDto userDto3 = UserDto.builder()
          .userId("10002")
          .name("きり丸3")
          .build();
      usersMapper.insert(userDto);
      usersMapper.insert(userDto2);
      usersMapper.insert(userDto3);

      DepartmentDto departmentDto1 = DepartmentDto.builder()
          .departmentId("100")
          .name("プロダクト部")
          .userList(List.of(userDto, userDto2))
          .build();
      DepartmentDto departmentDto2 = DepartmentDto.builder()
          .departmentId("101")
          .name("プロダクト部開発課")
          .userList(List.of(userDto3))
          .build();
      DepartmentDto departmentDto3 = DepartmentDto.builder()
          .departmentId("102")
          .name("プロダクト部マネージメント課")
          .build();

      departmentMapper.insert(departmentDto1);
      departmentMapper.insert(departmentDto2);
      departmentMapper.insert(departmentDto3);

      CompanyDto entity = CompanyDto.builder()
          .companyId("1")
          .name("親企業")
          .departmentList(List.of(departmentDto1, departmentDto2, departmentDto3))
          .build();

      mapper.insert(entity);

      // 関連テーブル
      DepartmentUserDto departmentUserDto1 = DepartmentUserDto.builder()
          .departmentId(departmentDto1.getDepartmentId())
          .userId(userDto.getUserId())
          .build();
      DepartmentUserDto departmentUserDto2 = DepartmentUserDto.builder()
          .departmentId(departmentDto1.getDepartmentId())
          .userId(userDto2.getUserId())
          .build();
      DepartmentUserDto departmentUserDto3 = DepartmentUserDto.builder()
          .departmentId(departmentDto2.getDepartmentId())
          .userId(userDto3.getUserId())
          .build();
      insert(departmentUserDto1, departmentUserDto2, departmentUserDto3);

      CompanyDepartmentDto companyDepartmentDto1 = CompanyDepartmentDto.builder()
          .companyId(entity.getCompanyId())
          .departmentId(departmentDto1.getDepartmentId())
          .build();
      CompanyDepartmentDto companyDepartmentDto2 = CompanyDepartmentDto.builder()
          .companyId(entity.getCompanyId())
          .departmentId(departmentDto2.getDepartmentId())
          .build();
      CompanyDepartmentDto companyDepartmentDto3 = CompanyDepartmentDto.builder()
          .companyId(entity.getCompanyId())
          .departmentId(departmentDto3.getDepartmentId())
          .build();

      insertCompanyDepartment(companyDepartmentDto1, companyDepartmentDto2, companyDepartmentDto3);

      // WHEN & THEN
      CompanyDto actual = mapper.findByPrimaryKey("1");
      assertThat(
          actual
      ).isEqualTo(entity);
    }
  }
}