package kirimaru.biz.mapper.dto;

import kirimaru.biz.domain.book.Isbn;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {
  private String isbn;
  private int money;
  private String author;
  private LocalDateTime generateDate;
  private String generateUser;
  private LocalDateTime updateDate;
  private String updateUser;
}
