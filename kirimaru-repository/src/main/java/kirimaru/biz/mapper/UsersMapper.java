package kirimaru.biz.mapper;

import java.util.List;
import kirimaru.biz.domain.constant.CodeConstant.DbTable;
import kirimaru.biz.mapper.dto.DepartmentDto;
import kirimaru.biz.mapper.dto.UserDto;
import kirimaru.biz.mapper.helper.InsertScriptBuilder;
import kirimaru.biz.mapper.helper.UpdateScriptBuilder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface UsersMapper {

  @Select("""
      SELECT * 
      FROM USERS
      WHERE USER_ID = #{userId}
      """)
  UserDto findByPrimaryKey(@Param("userId") String id);

  @Select("""
      SELECT USERS.*
       FROM DEPARTMENT_MEMBER, USERS
       WHERE DEPARTMENT_MEMBER.USER_ID = USERS.USER_ID
       AND DEPARTMENT_ID = #{departmentId}
      """)
  List<UserDto> findByDepartmentId(@Param("departmentId") String id);


  @InsertProvider(type = UsersMapper.ScriptBuilder.class, method = "insert")
  int insert(UserDto user);

  @UpdateProvider(type = UsersMapper.ScriptBuilder.class, method = "update")
  int update(UserDto user);

  class ScriptBuilder {
    public String update(UserDto entity) {
      return new UpdateScriptBuilder()
          .table(DbTable.USERS)
          .field("name", "name", entity.getName())
          .build();
    }


    public String insert(UserDto entity) {
      return new InsertScriptBuilder()
          .table(DbTable.USERS)
          .field("user_id", "userId", entity.getUserId())
          .field("name", "name", entity.getName())
          .build();
    }
  }
}

