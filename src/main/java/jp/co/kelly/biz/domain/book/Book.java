package jp.co.kelly.biz.domain.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  private Isbn id;
  private int money;
  private String author;
  private LocalDateTime generateDate;
  private String generateUser;
  private LocalDateTime updateDate;
  private String updateUser;
}
