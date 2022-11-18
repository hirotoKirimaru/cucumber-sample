package kirimaru.biz.mapper.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import kirimaru.biz.domain.constant.CodeConstant;
import lombok.AllArgsConstructor;

public class UpdateScriptBuilder {

  private CodeConstant.DbTable table;
  private List<Field> field = new ArrayList();
  private boolean commonColumn = false;

  public String build() {
    var sb = new StringBuilder();

    sb.append("<script>");

    sb.append("UPDATE ")
        .append(table.getTable())
        .append(" SET ")
    ;

//    var sj = new StringJoiner(",", "(", ")");
//    var sj = new StringBuilder();
    for (Field field1 : field) {
      if (field1.attribute != null) {
        sb.append(field1.columnName);
        sb.append("=");
        sb.append("'").append(field1.attribute).append("'");
      }
    }
//    if (commonColumn) {
//      sj.add("generate_date");
//      sj.add("generate_user");
//      sj.add("update_date");
//      sj.add("update_user");
//    }

//    sb.append(sj).append(" VALUES ");

//    sj = new StringJoiner(",", "(", ")");
//    for (Field field1 : field) {
//      if (field1.attribute != null) {
//        sj.add("#{" + field1.attributeName + "}");
//      }
//    }
//    sj.add("'" + LocalDateTime.of(2021, 10, 1, 2, 3).toString() + "'");
//    sj.add("'kirimaru'");
//    sj.add("'" + LocalDateTime.of(2021, 10, 1, 2, 3).toString() + "'");
//    sj.add("'kirimaru'");

//    if (commonColumn) {
//      sj.add("'" + StaticBeanAccessor.getNowString() + "'");
//      sj.add("'" + StaticBeanAccessor.getUser() + "'");
//      sj.add("'" + StaticBeanAccessor.getNowString() + "'");
//      sj.add("'" + StaticBeanAccessor.getUser() + "'");
//    }


//    sb.append(sj);
    sb.append("</script>");

    return sb.toString();
  }

  public UpdateScriptBuilder table(CodeConstant.DbTable tableName) {
    this.table = tableName;
    return this;
  }

  public UpdateScriptBuilder field(String columnName, String attributeName, Object attribute) {
    this.field.add(new Field(columnName, attributeName, attribute));
    return this;
  }

  public UpdateScriptBuilder commonUpdateColumn(boolean bool) {
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

