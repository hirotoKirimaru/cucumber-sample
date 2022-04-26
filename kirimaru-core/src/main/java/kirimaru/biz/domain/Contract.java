package kirimaru.biz.domain;

import java.time.temporal.ChronoUnit;
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
   * 契約日と解約日の間に暦上で何か月の差分があるかを計算する。
   * 端数がある場合は、切り上げる。
   * NOTE: Nヵ月後の日付の場合を、1ヵ月とする場合。
   * 01/01～02/01 = 1 ヵ月 + あまり0日 = 1ヵ月
   * DBとして開始日、終了日は0101～0201.
   */
  public int computeBetweenMonthsRoundUpIncludeEndDate() {
    long between = ChronoUnit.MONTHS.between(contractDate, expireDate);
    LocalDate localDate = contractDate.plusMonths(between);
    int i = ChronoUnit.DAYS.between(localDate, expireDate) == 0 ? 0 : 1;

    return (int) between + i;
  }

  /**
   * 契約日と解約日の間に暦上で何か月の差分があるかを計算する。
   * 端数がある場合は、切り上げる。
   * NOTE: Nヵ月後の日付の場合を、1ヵ月+1とする場合。
   * 01/01～02/01 = 1 ヵ月 + あまり1日 = 2ヵ月
   * DBとして開始日、終了日は0101～0131.
   */
  public int computeBetweenMonthsRoundUpExcludeEndDate() {
    return (int) ChronoUnit.MONTHS.between(contractDate, expireDate) + 1;
  }


  /**
   * ※ 法律に従ったバージョン
   * <p>
   * 解約日は契約日からキッチリ1カ月単位で割り切れる場合は解約できる。
   * </p>
   * |場合|算出されるべき満了日|根拠|具体例| |---|---|---|---| |月の初日から起算する場合|最終月の末日|民法１４３条２項本文|1月1日から起算して2か月は，平年なら2月28日，閏年なら2月29日が満了日。1月1日から起算して3か月は，3月31日が満了日|
   * |月の途中から起算し，最終月に応当日のある場合|最終月の応当日の前日|民法１４３条２項本文|1月20日から起算して2か月は，3月19日が満了日。1月31日から起算して2か月は，3月30日が満了日|
   * |月の途中から起算し，最終月に応当日のない場合|最終月の末日|民法１４３条２項但書|1月31日から起算して1か月は，平年なら2月28日，閏年なら2月29日が満了日。3月31日から起算して1か月は，4月30日が満了日|
   *
   * </p>
   * |条件|開始日|満了日|日割が発生しない|備考| |---|---|---|---|---| |1|2020/12/01|2021/03/31|○|開始日月初で満了日月末|
   * |x|2020/12/01|2021/03/30|×|開始日月初で満了日月末-1日| |1|2020/12/01|2021/02/28|○|開始日月初で満了日が閏年ではない月末|
   * |1|2020/12/01|2024/02/29|○|開始日月初で満了日が閏年の月末| |2|2020/12/10|2021/02/09|○|開始日月央で満了日 -1日|
   * |x|2020/12/10|2021/12/10|×|開始日月央で満了日が同日| |x|2020/02/29|2024/11/30|×|開始日月末(29)で満了日が月末(30)|
   * |x|2020/02/29|2024/12/31|×|開始日月末(29)で満了日が月末(31)| |x|2020/11/30|2021/03/31|×|開始日月末(30)で満了日が月末(31)|
   * |2,3|2020/12/31|2021/04/30|○|開始日月末(31)で満了日が月末(30)| |x|2020/12/31|2021/03/31|×|開始日月末(31)で満了日が月末(31)|
   * |3|2020/12/31|2021/02/28|○|開始日月末で満了日が閏年ではない月末| |3|2020/12/31|2024/02/29|○|開始日月末で満了日が閏年の月末|
   * |x|2020/12/30|2024/02/28|×|開始日月末 -1日で満了日が閏年の月末 -1日| |2,3|2020/12/30|2024/02/29|○|開始日月末
   * -1日で満了日が閏年の月末|
   */
  public boolean canExpire() {
    // 月の初日から起算する場合は、最終月の末日
    if (contractDate.getDayOfMonth() == 1) {
      if (expireDate.plusDays(1).getDayOfMonth() == 1) {
        return true;
      }
    }
    // 月の途中から起算し，最終月に応当日のある場合は、最終月の応当日の前日
    // -1日すると、月を跨ぐ可能性があるのでNG
    if (contractDate.getDayOfMonth() == expireDate.plusDays(1).getDayOfMonth()) {
      return true;
    }

    // 月の途中から起算し，最終月に応当日のない場合は、最終月の末日
    if (contractDate.getDayOfMonth() > expireDate.getDayOfMonth()) {
      if (expireDate.plusDays(1).getDayOfMonth() == 1) {
        return true;
      }
    }

    return false;
  }

  /**
   * ※ 法律を知らなかったオレオレ解約ルール。ただ、奇跡的に上手く設計できていたようだ。
   *
   * <p>
   * 解約日は契約日からキッチリ1カ月単位で割り切れる場合は解約できる。
   * </p>
   * <p>
   * 1. 契約日の日 -1 - 解約日の日なら解約できる。(2020年12月10日に契約したら、2021年2月9日なら解約できる。) 2.
   * 契約日が月頭、解約日が月末なら解約できる（2020年12月1月に契約したら、2021年3月31日に解約できる。) 3. 契約日が2月以外の月末、解約日が2月の月末なら解約できる。(2020年12月31日に契約したら、2021年2月28日に解約できる)
   * ※ 2月は日付が足りないので特別扱いをする。 ・月末が30日の月に契約すると、実質2月以外の月末では解約できない。 ・月末が31日の月に契約すると、月末が30日の月にも解約できる
   * ・解約日を月末にこだわらなければ、契約日-1日でいつでも解約は可能 ※ 月末解約・一覧表（契約日の-1日で解約すればどの月でも解約は可能) |契約日|解約可能月| |---|---|
   * |1月|2月, 4月, 6月, 9月, 11月| |2月|2月| |3月|2月, 4月, 6月, 9月, 11月| |4月|2月| |5月|2月, 4月, 6月, 9月, 11月|
   * |6月|2月| |7月|2月, 4月, 6月, 9月, 11月| |8月|2月, 4月, 6月, 9月, 11月| |9月|2月| |10月|2月, 4月, 6月, 9月, 11月|
   * |11月|2月| |12月|2月, 4月, 6月, 9月, 11月|
   *
   * </p>
   * |条件|契約日|解約日|可能|備考| |---|---|---|---|---| |1|2020/12/10|2021/02/09|true|1日前|
   * |x|2020/12/10|2021/02/10|false|同日| |2|2020/12/01|2021/03/31|true|月頭で月末|
   * |x|2020/12/01|2021/03/30|false|月頭で月末-1日| |2|2020/12/01|2021/02/28|true|Not うるう|
   * |2|2020/12/01|2024/02/29|true|うるう| |1|2020/12/31|2021/03/30|true|1日前|
   * |1|2020/12/31|2021/04/30|true|月末と月末(1日前)| |x|2020/12/31|2021/03/31|false|同日|
   * |x|2020/11/30|2021/03/31|false|1日後| |3|2020/12/31|2021/02/28|true|Not うるう|
   * |3|2020/12/31|2024/02/29|true|うるう| |x|2020/02/29|2024/12/31|false|2日後|
   * |x|2020/02/29|2024/11/30|false|1日後|
   */
  @Deprecated(since = "法律に従ってない")
  public boolean canExpire2() {
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
