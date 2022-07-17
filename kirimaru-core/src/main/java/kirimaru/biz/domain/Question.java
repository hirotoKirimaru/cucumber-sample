package kirimaru.biz.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Question {

  /**
   * ID.
   */
  private int id;
  /**
   * 問題文.
   */
  private String questionSentence;

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
