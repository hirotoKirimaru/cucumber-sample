package kirimaru.biz.mapper.helper;

import kirimaru.biz.domain.constant.CodeConstant;
import kirimaru.biz.mapper.dto.BookDto;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class InsertScriptBuilder {
  private CodeConstant.DbTable table;
  private List<Field> field = new ArrayList();

  public String build() {
    var sb = new StringBuilder();

    sb.append("<script>");

    sb.append("INSERT INTO ").append(table.getTable()).append("(");

    var sj = new StringJoiner(",");
    for (Field field1 : field) {
      if (field1.attribute != null) {
        sj.add(field1.columnName);
      }
    }
    sb.append(sj).append(") VALUES (");

    sj = new StringJoiner(",");
    for (Field field1 : field) {
      if (field1.attribute != null) {
        sj.add("#{" + field1.attributeName + "}");
      }
    }
    sb.append(sj);
    sb.append(")");
    sb.append("</script>");

    return sb.toString();
  }

  public InsertScriptBuilder table(CodeConstant.DbTable tableName) {
    this.table = tableName;
    return this;
  }

  public InsertScriptBuilder field(String columnName, String attributeName, Object attribute) {
    this.field.add(new Field(columnName, attributeName, attribute));
    return this;
  }


  @AllArgsConstructor
  private class Field {
    private final String columnName;
    private final String attributeName;
    private final Object attribute;
  }
}

