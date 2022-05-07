package kirimaru.biz.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 *
 */
//@Value
@Data
@AllArgsConstructor
//@Builder
//@Getter
//@Accessors(fluent = true, chain = true, prefix = "as") # NOTE: prefixはIntelliJ IDEAではうまく動かない模様。
@Accessors(fluent = true, chain = true)
//@Accessors(fluent = true)
//@Accessors(chain = true)
public class RequestDate {

  @NonNull
//  private final String string;
  //  @Accessors(fluent = true, chain = true, prefix = "as")
  private String string;
  @NonNull
//  private final LocalDate localDate;
//  @Accessors(fluent = true, chain = true, prefix = "as")
  private LocalDate localDate;

  private static final DateTimeFormatter SHORT_DATE_FORMATTER =
      DateTimeFormatter.ofPattern("uuuuMMdd")
          .withResolverStyle(ResolverStyle.STRICT);

  public static RequestDate of(String value) {
    return new RequestDate(value, LocalDate.parse(value, SHORT_DATE_FORMATTER));
  }

}