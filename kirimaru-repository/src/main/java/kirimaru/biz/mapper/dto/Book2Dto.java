package kirimaru.biz.mapper.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import kirimaru.biz.domain.book.Isbn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book2Dto implements Serializable {
  private Isbn isbn;
  private int money;
  private String author;
  private LocalDateTime generateDate;
  private String generateUser;
  private LocalDateTime updateDate;
  private String updateUser;
}
