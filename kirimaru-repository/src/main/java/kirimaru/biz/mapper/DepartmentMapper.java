package kirimaru.biz.mapper;

import java.util.List;
import kirimaru.biz.domain.constant.CodeConstant.DbTable;
import kirimaru.biz.mapper.dto.DepartmentDto;
import kirimaru.biz.mapper.helper.InsertScriptBuilder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface DepartmentMapper {

  @Results(id = "department",
      value = {
          @Result(id = true, column = "department_id", property = "departmentId"),
          @Result(column = "department_id", property = "userList",
              many = @Many(
                  select = "kirimaru.biz.mapper.UsersMapper.findByDepartmentId", fetchType = FetchType.EAGER)
          )
      }
  )
  @Select("""
      SELECT * 
      FROM DEPARTMENT
      WHERE DEPARTMENT_ID = #{departmentId}
      """)
  DepartmentDto findByPrimaryKey(@Param("departmentId") String id);

  @ResultMap("department")
  @Select("""
      SELECT DEPARTMENT.*
       FROM DEPARTMENT, COMPANY_DEPARTMENT
       WHERE DEPARTMENT.DEPARTMENT_ID = COMPANY_DEPARTMENT.DEPARTMENT_ID
       AND COMPANY_ID = #{companyId}
      """)
  List<DepartmentDto> findByCompanyId(@Param("companyId") String id);

  @Results(id = "departmentLazy",
      value = {
          @Result(id = true, column = "department_id", property = "departmentId"),
          @Result(column = "department_id", property = "userList",
              many = @Many(
                  select = "kirimaru.biz.mapper.UsersMapper.findByDepartmentId", fetchType = FetchType.LAZY
              )
          )
      }
  )
    @Select("""
      SELECT DEPARTMENT.*
       FROM DEPARTMENT, COMPANY_DEPARTMENT
       WHERE DEPARTMENT.DEPARTMENT_ID = COMPANY_DEPARTMENT.DEPARTMENT_ID
       AND COMPANY_ID = #{companyId}
      """)
  List<DepartmentDto> findByCompanyIdAndLazy(@Param("companyId") String id);

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

