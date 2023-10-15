package kirimaru.biz.domain;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
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

  public static RequestDate of(LocalDate value) {
    return new RequestDate(value.format(SHORT_DATE_FORMATTER), value);
  }

  /**
   * 開始日から、年月を元に日割りなしの日付を取得する。
   *
   * @param endMonth 終わり日付
   */
  public LocalDate from年月to日割りなし日付(LocalDate endMonth) {
    YearMonth yearMonth = YearMonth.from(endMonth);
    if (yearMonth.isValidDay(localDate.getDayOfMonth() - 1)) {
      return yearMonth.atDay(localDate.getDayOfMonth() - 1);
    } else {
      return yearMonth.atEndOfMonth();
    }
  }
}