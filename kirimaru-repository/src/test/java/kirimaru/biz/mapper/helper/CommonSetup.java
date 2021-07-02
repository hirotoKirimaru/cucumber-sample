package kirimaru.biz.mapper.helper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import kirimaru.biz.domain.book.Isbn;
import kirimaru.biz.domain.constant.CodeConstant;
import kirimaru.biz.domain.book.Book;
import kirimaru.biz.mapper.dto.Book2Dto;
import kirimaru.biz.mapper.dto.BookDto;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MybatisTest
public class CommonSetup {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  DataSource dataSource;

  SimpleJdbcInsert simpleJdbcInsert;

  public static LocalDateTime now = LocalDateTime.of(2021, 10, 1, 2, 3);

  protected List<BookDto> findBookList() {
    return jdbcTemplate.query("SELECT * FROM BOOK", getBookRowMapper());
  }

  protected List<Book2Dto> findBook2List() {
    return jdbcTemplate.query("SELECT * FROM BOOK", getBook2RowMapper());
  }

  private RowMapper<BookDto> getBookRowMapper() {
    return (rs, i) ->
        BookDto.builder()
            .isbn(rs.getString("isbn"))
            .money(rs.getInt("money"))
            .author(rs.getString("author"))
            .generateDate(rs.getObject("generate_date", LocalDateTime.class))
            .generateUser(rs.getString("generate_user"))
            .updateDate(rs.getObject("update_date", LocalDateTime.class))
            .updateUser(rs.getString("update_user"))
            .build();
  }

  private RowMapper<Book2Dto> getBook2RowMapper() {
    return (rs, i) ->
        Book2Dto.builder()
            .isbn(rs.getObject("isbn", Isbn.class))
            .money(rs.getInt("money"))
            .author(rs.getString("author"))
            .generateDate(rs.getObject("generate_date", LocalDateTime.class))
            .generateUser(rs.getString("generate_user"))
            .updateDate(rs.getObject("update_date", LocalDateTime.class))
            .updateUser(rs.getString("update_user"))
            .build();
  }


  protected void insertBooks(Book... records) {
    this.simpleJdbcInsert =
        new SimpleJdbcInsert(dataSource)
            .withTableName(CodeConstant.DbTable.BOOK.getTable())
    ;
    for (var record : records) {
      this.simpleJdbcInsert.execute(toMap(record));
    }

  }

  public Map<String, Object> toMap(Serializable entity) {
    HashMap<String, Object> rtn = new HashMap<>();
    var source = new CustomBeanPropertySqlParameterSource(entity);

    for (String readablePropertyName : source.getReadablePropertyNames()) {
      // クラス名は不要
      if (List.of("class", "blank", "bytes", "empty").contains(readablePropertyName)) {
        continue;
      }

      var snakeName = new PropertyNamingStrategy.SnakeCaseStrategy().translate(readablePropertyName);
      Object object = source.getValue(readablePropertyName);

      if (object instanceof Serializable) {
        var primaryKeyMap = toMap((Serializable) object);
        rtn.putAll(primaryKeyMap);
      }

      rtn.put(snakeName, object);
    }

    return rtn;
  }

}
