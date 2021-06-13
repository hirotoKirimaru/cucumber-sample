package kirimaru.biz.mapper;

import kirimaru.biz.domain.book.Book;
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
  List<Book> findAll();


}
