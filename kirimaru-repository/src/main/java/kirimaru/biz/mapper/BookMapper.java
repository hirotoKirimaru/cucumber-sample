package kirimaru.biz.mapper;

import java.util.List;
import kirimaru.biz.domain.constant.CodeConstant;
import kirimaru.biz.mapper.dto.BookDto;
import kirimaru.biz.mapper.helper.InsertScriptBuilder;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    public String insert(BookDto record) {
      return new InsertScriptBuilder()
          .table(CodeConstant.DbTable.BOOK)
          .field("isbn", "isbn", record.getIsbn())
          .field("money", "money", record.getMoney())
          .field("author", "author", record.getAuthor())
          .field("number", "number", record.getNumber())
//          .field("generate_date", "generateDate", record.getGenerateDate())
//          .field("generate_user", "generateUser", record.getGenerateUser())
//          .field("update_date", "updateDate", record.getUpdateDate())
//          .field("update_user", "updateUser", record.getUpdateUser())
          .commonUpdateColumn(true)
          .build();
    }
  }
}

