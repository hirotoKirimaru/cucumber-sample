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
   * (※ 月末と月末は2月以外はNG)
   * </p>
   * |条件|契約日|解約日|可能|備考|
   * |---|---|---|---|---|
   * |1|2020/12/10|2021/02/09|true||
   * |x|2020/12/10|2021/02/10|false||
   * |2|2020/12/01|2021/03/31|true||
   * |x|2020/12/01|2021/03/30|false||
   * |2|2020/12/01|2021/02/28|true||
   * |2|2020/12/01|2024/02/29|true|うるう|
   * |1|2020/12/31|2021/03/30|true||
   * |x|2020/12/31|2021/03/31|false||
   * |x|2020/11/30|2021/03/31|false||
   * |3|2020/12/31|2021/02/28|true||
   * |3|2020/12/31|2024/02/29|true|うるう|
   * |x|2020/02/29|2024/12/31|false||
   * |x|2020/02/29|2024/11/30|false||
   */
  public boolean canExpire() {
    return true;
  }
}
