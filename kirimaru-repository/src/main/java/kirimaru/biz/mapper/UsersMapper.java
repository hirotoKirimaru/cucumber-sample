package kirimaru.biz.mapper;

import kirimaru.biz.domain.constant.CodeConstant.DbTable;
import kirimaru.biz.mapper.dto.UserDto;
import kirimaru.biz.mapper.helper.InsertScriptBuilder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper {

  @Select("""
      SELECT * 
      FROM USERS
      WHERE USER_ID = #{userId}
      """)
  UserDto findByPrimaryKey(@Param("userId") String id);

  @InsertProvider(type = UsersMapper.ScriptBuilder.class, method = "insert")
  int insert(@Param("user") UserDto user);

  class ScriptBuilder {

    public String insert(UserDto entity) {
      return new InsertScriptBuilder()
          .table(DbTable.USERS)
          .field("user_id", "userId", entity.getUserId())
          .field("name", "name", entity.getName())
          .build();
    }
  }
}

