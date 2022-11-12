package kirimaru.biz.mapper;

import java.util.List;
import kirimaru.biz.domain.constant.CodeConstant.DbTable;
import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.biz.mapper.dto.DepartmentDto;
import kirimaru.biz.mapper.helper.InsertScriptBuilder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {

  @Select("""
      SELECT * 
      FROM DEPARTMENT
      WHERE DEPARTMENT_ID = #{departmentId}
      """)
  DepartmentDto findByPrimaryKey(@Param("departmentId") String id);

  @Select("""
      SELECT DEPARTMENT.*
       FROM DEPARTMENT, COMPANY_DEPARTMENT
       WHERE DEPARTMENT.DEPARTMENT_ID = COMPANY_DEPARTMENT.DEPARTMENT_ID
       AND COMPANY_ID = #{companyId}
      """)
  List<DepartmentDto> findByCompanyId(@Param("companyId") String id);

  @InsertProvider(type = DepartmentMapper.ScriptBuilder.class, method = "insert")
  int insert(DepartmentDto entity);

  class ScriptBuilder {

    public String insert(DepartmentDto entity) {
      return new InsertScriptBuilder()
          .table(DbTable.DEPARTMENT)
          .field("department_id", "departmentId", entity.getDepartmentId())
          .field("name", "name", entity.getName())
          .build();
    }
  }
}

