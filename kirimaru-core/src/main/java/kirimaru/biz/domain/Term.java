package kirimaru.biz.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * 日付の前後関係をなんやかんや
 */
@Value
@Builder
public class Term {

  /**
   * 契約日 開始日
   */
  @NonNull
  private final LocalDate start;
  /**
   * 解約日 終了日
   */
  @NonNull
  private final LocalDate end;

  public record Term2(LocalDate start, LocalDate end) {
    public boolean isOverlap(
        @NonNull Term2 param
    ) {
      return !start.isAfter(param.end()) && !param.start().isAfter(end);
    }
  }

  /**
   * 契約日と解約日の間に暦上で何か月の差分があるかを計算する。 端数がある場合は、切り上げる。 NOTE: Nヵ月後の日付の場合を、1ヵ月とする場合。 01/01～02/01 = 1 ヵ月 +
   * あまり0日 = 1ヵ月 DBとして開始日、終了日は0101～0201.
   */
  @Deprecated(since = "いつ使う❔")
  public int computeBetweenMonthsRoundUpIncludeEndDate() {
    long between = ChronoUnit.MONTHS.between(start, end);
    LocalDate localDate = start.plusMonths(between);
    int i = ChronoUnit.DAYS.between(localDate, end) == 0 ? 0 : 1;

    return (int) between + i;
  }

  /**
   * 契約日と解約日の間に暦上で何か月の差分があるかを計算する。 端数がある場合は、切り上げる。 NOTE: Nヵ月後の日付の場合を、1ヵ月+1とする場合。 01/01～01/31 = 0 ヵ月
   * + あまり30日 = 1ヵ月 01/01～02/01 = 1 ヵ月 + あまり1日 = 2ヵ月 DBとして開始日、終了日は0101～0131.
   */
  public int computeBetweenMonthsRoundUp() {
    return (int) ChronoUnit.MONTHS.between(start, end) + 1;
  }


  /**
   * ※ 法律に従ったバージョン
   * <p>
   * 解約日は契約日からキッチリ1カ月単位で割り切れる場合は解約できる。
   * </p>
   * |場合|算出されるべき満了日|根拠|具体例| |---|---|---|---|
   * |月の初日から起算する場合|最終月の末日|民法１４３条２項本文|1月1日から起算して2か月は，平年なら2月28日，閏年なら2月29日が満了日。1月1日から起算して3か月は，3月31日が満了日|
   * |月の途中から起算し，最終月に応当日のある場合|最終月の応当日の前日|民法１４３条２項本文|1月20日から起算して2か月は，3月19日が満了日。1月31日から起算して2か月は，3月30日が満了日|
   * |月の途中から起算し，最終月に応当日のない場合|最終月の末日|民法１４３条２項但書|1月31日から起算して1か月は，平年なら2月28日，閏年なら2月29日が満了日。3月31日から起算して1か月は，4月30日が満了日|
   *
   * </p>
   * |条件|開始日|満了日|日割が発生しない|備考| |---|---|---|---|---| |1|2020/12/01|2021/03/31|○|開始日月初で満了日月末|
   * |x|2020/12/01|2021/03/30|×|開始日月初で満了日月末-1日| |1|2020/12/01|2021/02/28|○|開始日月初で満了日が閏年ではない月末|
   * |1|2020/12/01|2024/02/29|○|開始日月初で満了日が閏年の月末| |2|2020/12/10|2021/02/09|○|開始日月央で満了日 -1日|
   * |x|2020/12/10|2021/12/10|×|開始日月央で満了日が同日| |x|2020/02/29|2024/11/30|×|開始日月末(29)で満了日が月末(30)|
   * |x|2020/02/29|2024/12/31|×|開始日月末(29)で満了日が月末(31)|
   * |x|2020/11/30|2021/03/31|×|開始日月末(30)で満了日が月末(31)|
   * |2,3|2020/12/31|2021/04/30|○|開始日月末(31)で満了日が月末(30)|
   * |x|2020/12/31|2021/03/31|×|開始日月末(31)で満了日が月末(31)| |3|2020/12/31|2021/02/28|○|開始日月末で満了日が閏年ではない月末|
   * |3|2020/12/31|2024/02/29|○|開始日月末で満了日が閏年の月末| |x|2020/12/30|2024/02/28|×|開始日月末 -1日で満了日が閏年の月末 -1日|
   * |2,3|2020/12/30|2024/02/29|○|開始日月末 -1日で満了日が閏年の月末|
   */
  public boolean canExpire() {
    // 月の初日から起算する場合は、最終月の末日
    if (start.getDayOfMonth() == 1) {
      if (end.plusDays(1).getDayOfMonth() == 1) {
        return true;
      }
    }
    // 月の途中から起算し，最終月に応当日のある場合は、最終月の応当日の前日
    // -1日すると、月を跨ぐ可能性があるのでNG
    if (start.getDayOfMonth() == end.plusDays(1).getDayOfMonth()) {
      return true;
    }

    // 月の途中から起算し，最終月に応当日のない場合は、最終月の末日
    if (start.getDayOfMonth() > end.getDayOfMonth()) {
      if (end.plusDays(1).getDayOfMonth() == 1) {
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
   * 契約日が月頭、解約日が月末なら解約できる（2020年12月1月に契約したら、2021年3月31日に解約できる。) 3.
   * 契約日が2月以外の月末、解約日が2月の月末なら解約できる。(2020年12月31日に契約したら、2021年2月28日に解約できる) ※ 2月は日付が足りないので特別扱いをする。
   * ・月末が30日の月に契約すると、実質2月以外の月末では解約できない。 ・月末が31日の月に契約すると、月末が30日の月にも解約できる
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
    if (start.getDayOfMonth() - 1 == end.getDayOfMonth()) {
      return true;
    }
    // 契約日が月頭、解約日が月末なら解約できる
    if (start.getDayOfMonth() == 1) {
      if (end.plusDays(1).getDayOfMonth() == 1) {
        return true;
      }
    }

    // 契約日が2月以外の月末、解約日が2月の月末なら解約できる。
    if (start.getMonthValue() == 2) {
      return false;
    }

    if (end.getMonthValue() == 2) {
      if (end.plusDays(1).getDayOfMonth() == 1) {
        return true;
      }
    }

    return false;
  }

  /**
   * 開始日と終了日の前後関係をチェックする 異常な場合にtrueを設定する。
   *
   * @return |start|end|返却値| |---|---|---| |2022/01/15|2022/01/16|false|
   * |2022/01/15|2022/01/15|false| |2022/01/16|2022/01/15|true|
   */
  public boolean isIllegalSituation() {
    return start.isAfter(end);
  }

  /**
   * パラメータの日付がこのstartとendの間の期間内であるか。 なお、endと同一は含まないとする。
   *
   * @param baseDate 基準日付
   * @return true=期間内
   */
  public boolean isDuringExcludeEndDate(@NonNull LocalDate baseDate) {
    return (!baseDate.isBefore(start)) && baseDate.isBefore(end);
  }

  /**
   * パラメータの日付がこのstartとendの間の期間内であるか。 なお、endと同一は含むとする。
   *
   * @param baseDate 基準日付
   * @return true=期間内
   */
  public boolean isDuringIncludeEndDate(@NonNull LocalDate baseDate) {
    return (!baseDate.isBefore(start)) && (!baseDate.isAfter(end));
  }

  /**
   * パラメータの日付がこのstartとendの間の期間を掠るか。
   * <p>
   *「比較開始日付 <= 対象終了日付 AND 比較終了日付 >= 対象開始日付」
   * https://qiita.com/yaju/items/a58a78f41ee41258a5fe
   * </p>
   * @param param 別の期間
   * @return true=期間内
   */
  public boolean isOverlap(
      @NonNull Term param
  ) {
    return !start.isAfter(param.getEnd()) && !param.getStart().isAfter(end);
  }

}
