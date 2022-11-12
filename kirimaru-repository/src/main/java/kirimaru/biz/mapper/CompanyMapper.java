package kirimaru.biz.mapper;

import kirimaru.biz.domain.constant.CodeConstant.DbTable;
import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.biz.mapper.dto.UserDto;
import kirimaru.biz.mapper.helper.InsertScriptBuilder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CompanyMapper {

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
      return new InsertScriptBuilder()
          .table(DbTable.COMPANY)
          .field("company_id", "companyId", entity.getCompanyId())
          .field("name", "name", entity.getName())
          .commonUpdateColumn(false)
          .build();
    }
  }
}

