package kirimaru.biz.domain;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Value
@Builder
public class Contract {
  /**
   * 契約日
   */
  private final LocalDate contractDate;
  /**
   * 解約日
   */
  @Nullable
  private final LocalDate expireDate;

  /**
   * <p>
   * 解約日は契約日からキッチリ1カ月単位で割り切れる場合は解約できる。
   * </p>
   * <p>
   * 1. 契約日の日 -1 - 解約日の日なら解約できる。(2020年12月10日に契約したら、2021年2月9日なら解約できる。)
   * 2. 契約日が月頭、解約日が月末なら解約できる（2020年12月1月に契約したら、2021年3月31日に解約できる。)
   * 3. 契約日が2月以外の月末、解約日が2月の月末なら解約できる。(2020年12月31日に契約したら、2021年2月28日に解約できる)
   * (※
   * ・月末と月末は2月以外はNG。
   * ・月末が30日の月に契約すると、実質2月以外では解約できない。
   * ・月末が31日の月に契約すると、月末が30日の月にも解約できる)
   * </p>
   * |条件|契約日|解約日|可能|備考|
   * |---|---|---|---|---|
   * |1|2020/12/10|2021/02/09|true|1日前|
   * |x|2020/12/10|2021/02/10|false|同日|
   * |2|2020/12/01|2021/03/31|true|月頭で月末|
   * |x|2020/12/01|2021/03/30|false|月頭で月末-1日|
   * |2|2020/12/01|2021/02/28|true|Not うるう|
   * |2|2020/12/01|2024/02/29|true|うるう|
   * |1|2020/12/31|2021/03/30|true|1日前|
   * |1|2020/12/31|2021/04/30|true|月末と月末(1日前)|
   * |x|2020/12/31|2021/03/31|false|同日|
   * |x|2020/11/30|2021/03/31|false|1日後|
   * |3|2020/12/31|2021/02/28|true|Not うるう|
   * |3|2020/12/31|2024/02/29|true|うるう|
   * |x|2020/02/29|2024/12/31|false|2日後|
   * |x|2020/02/29|2024/11/30|false|1日後|
   */
  public boolean canExpire() {
    // 契約日の日 -1 - 解約日の日なら解約できる。
    // 実際に+1や-1日すると、月を跨ぐのでNG
    if (contractDate.getDayOfMonth() - 1 == expireDate.getDayOfMonth()) {
      return true;
    }
    // 契約日が月頭、解約日が月末なら解約できる
    if (contractDate.getDayOfMonth() == 1) {
      if (expireDate.plusDays(1).getDayOfMonth() == 1) {
        return true;
      }
    }

    // 契約日が2月以外の月末、解約日が2月の月末なら解約できる。
    if (contractDate.getMonthValue() == 2) {
      return false;
    }

    if (expireDate.getMonthValue() == 2) {
      if (expireDate.plusDays(1).getDayOfMonth() == 1) {
        return true;
      }
    }

    return false;
  }
}
