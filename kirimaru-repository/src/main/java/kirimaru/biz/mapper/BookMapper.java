package kirimaru.biz.mapper;

import kirimaru.biz.mapper.dto.BookDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface BookMapper {

  @Select({"""
      SELECT * 
      FROM BOOK
      """})
  List<BookDto> findAll();

  @InsertProvider(type = BookMapper.ScriptBuilder.class, method = "insert")
  int insert(BookDto book);

  public static class ScriptBuilder {
    public String insert() {
      return new StringBuilder()
          .append(
              "INSERT INTO BOOK VALUES(#{isbn}, #{money}, #{author}, #{generateDate}, #{generateUser}, #{updateDate}, #{updateUser})"
          ).toString();
    }
  }
}

