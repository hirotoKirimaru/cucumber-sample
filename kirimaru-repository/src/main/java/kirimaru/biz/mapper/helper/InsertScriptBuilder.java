package kirimaru.biz.mapper.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import kirimaru.biz.domain.constant.CodeConstant;
import lombok.AllArgsConstructor;

public class InsertScriptBuilder {

  private CodeConstant.DbTable table;
  private List<Field> field = new ArrayList();
  private boolean commonColumn = false;

  public String build() {
    var sb = new StringBuilder();

    sb.append("<script>");

    sb.append("INSERT INTO ").append(table.getTable());

    var sj = new StringJoiner(",", "(", ")");
    for (Field field1 : field) {
      if (field1.attribute != null) {
        sj.add(field1.columnName);
      }
    }
    if (commonColumn) {
      sj.add("generate_date");
      sj.add("generate_user");
      sj.add("update_date");
      sj.add("update_user");

    }

    sb.append(sj).append(" VALUES ");

    sj = new StringJoiner(",", "(", ")");
    for (Field field1 : field) {
      if (field1.attribute != null) {
        sj.add("#{" + field1.attributeName + "}");
      }
    }
//    sj.add("'" + LocalDateTime.of(2021, 10, 1, 2, 3).toString() + "'");
//    sj.add("'kirimaru'");
//    sj.add("'" + LocalDateTime.of(2021, 10, 1, 2, 3).toString() + "'");
//    sj.add("'kirimaru'");

    if (commonColumn) {
      sj.add("'" + StaticBeanAccessor.getNowString() + "'");
      sj.add("'" + StaticBeanAccessor.getUser() + "'");
      sj.add("'" + StaticBeanAccessor.getNowString() + "'");
      sj.add("'" + StaticBeanAccessor.getUser() + "'");
    }

    sb.append(sj);
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

  public InsertScriptBuilder commonUpdateColumn(boolean bool) {
    this.commonColumn = bool;
    return this;
  }

  @AllArgsConstructor
  private class Field {

    private final String columnName;
    private final String attributeName;
    private final Object attribute;
  }
}

