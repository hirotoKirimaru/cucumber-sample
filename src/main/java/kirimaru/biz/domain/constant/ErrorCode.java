package kirimaru.biz.domain.constant;

import lombok.Getter;
import lombok.NonNull;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
public enum ErrorCode {
  E0001("E00001", "致命的なエラーが発生しました。"),
  E0002("E00002", "{0}は必須です。", 1),
  E0003("E00003", "{0}が{1}の時、{2}は必須です。", 3);
  private final String id;
  private final String message;
  private final int prepareNum;

  ErrorCode(String id, String message) {
    this.id = id;
    this.message = message;
    this.prepareNum = 0;
  }

  ErrorCode(String id, String message, int prepareNum) {
    this.id = id;
    this.message = message;
    this.prepareNum = prepareNum;
  }

  /**
   * ※ MessageFormatはスレッドセーフではないので、毎回生成すること。
   * 遅いことは遅いが、1マイクロ秒以下で生成されるので速度に大きな影響はない
   */
  public String fillMessages(@NonNull String... values) {
    validate(values);
    return MessageFormat.format(message, values);
  }


  private void validate(@NonNull String... values) {
    for (String value : values) {
      Objects.requireNonNull(value);
    }

    if (values.length != prepareNum) {
      throw new RuntimeException("コンパイルエラーにしたい！");
    }
  }

}
