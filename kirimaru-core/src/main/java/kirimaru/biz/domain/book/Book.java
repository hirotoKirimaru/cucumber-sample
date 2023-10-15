package kirimaru.biz.domain.book;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Book implements Serializable {
  private final Isbn id;
  private final int money;
  @Builder.Default
  private final String author = "kirimaru"; // デフォルト値
  private final LocalDateTime generateDate;
  private final String generateUser;
  private final LocalDateTime updateDate;
  private final String updateUser;
}
