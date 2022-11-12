package kirimaru.biz.mapper;

import kirimaru.biz.domain.constant.CodeConstant.DbTable;
import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.biz.mapper.dto.UserDto;
import kirimaru.biz.mapper.helper.InsertScriptBuilder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface CompanyMapper {

  @Results(id = "company",
      value = {
          @Result(id = true, column = "company_id", property = "companyId"),
          @Result(column = "company_id", property = "departmentList",
              many = @Many(
                  select = "kirimaru.biz.mapper.DepartmentMapper.findByCompanyId", fetchType = FetchType.EAGER)
          )
      }
  )
  @Select("""
      SELECT * 
      FROM COMPANY
      WHERE COMPANY_ID = #{companyId}
      """)
  CompanyDto findByPrimaryKey(@Param("companyId") String id);

  @InsertProvider(type = CompanyMapper.ScriptBuilder.class, method = "insert")
  int insert(CompanyDto user);

  class ScriptBuilder {

    public String insert(CompanyDto entity) {
      return new InsertScriptBuilder().table(DbTable.COMPANY)
          .field("company_id", "companyId", entity.getCompanyId())
          .field("name", "name", entity.getName()).commonUpdateColumn(false).build();
    }
  }
}

