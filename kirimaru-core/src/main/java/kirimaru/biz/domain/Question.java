package kirimaru.biz.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Question implements Serializable {

  /**
   * ID.
   */
  private int id;
  /**
   * 問題文.
   */
  private String questionSentence;
  /**
   * 問題文の言語
   */
  private Locale locale;


  /**
   * ジャンル.
   */
  private int genre;

  /**
   * 複数選択可能か.
   */
  private boolean multi;

  /**
   * 答え.
   */
  private int answer;

  /**
   * 製作日.
   */
  private LocalDateTime createTime;


}
