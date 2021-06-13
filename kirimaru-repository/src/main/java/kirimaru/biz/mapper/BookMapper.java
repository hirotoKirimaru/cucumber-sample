package kirimaru.biz.mapper;

import kirimaru.biz.mapper.dto.BookDto;
import org.apache.ibatis.annotations.Insert;
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


//  INSERT INTO BOOK VALUES('9784798126708', '1000', 'kirimaru', '2021/06/13', 'kirimaru', '2021/06/13', 'kirimaru')
  @Insert("""
          INSERT INTO BOOK VALUES(#{isbn}, #{money}, #{author}, #{generateDate}, #{generateUser}, #{updateDate}, #{updateUser})
      """
  )
  int insert(BookDto book);
}
