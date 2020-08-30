package jp.co.kelly.biz.domain.book;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Book {
  private final Isbn id;
  private final int money;
  @Builder.Default
  private final String author = "kirimaru"; // デフォルト値
  private final LocalDateTime generateDate;
  private final String generateUser;
  private final LocalDateTime updateDate;
  private final String updateUser;
}
