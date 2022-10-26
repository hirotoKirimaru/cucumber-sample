package kirimaru.biz.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.lang.Nullable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class AppString implements Serializable {

  private String value;

  public static AppString of(@Nullable String value) {
    if (value == null) {
      return new AppString("");
    }
    if (isIllegal(value)) {
      throw new RuntimeException("許容不可能な文字が含まれています");
    }
    return new AppString(value);
  }

  /**
   * SJISで変換して文字が異なったら異常
   */
  public static boolean isIllegal(@Nullable String value) {
    if (value == null) {
      return false;
    }
    try {
      // SJISでエンコードできなければOUT
      if (!value.equals(new String(value.getBytes("SJIS"), "SJIS"))) {
//      if (!value.equals(new String(value.getBytes("MS932"), "MS932"))) {

//        if (!value.equals(new String(value.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))) {
//      if (!value.equals(new String(value.getBytes("UTF-8"), "UTF-8"))) {
        return true;
      }
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  @Override
  public String toString() {
    return value == null ? "" : value;
  }

  /**
   * 規定のバイト数を超過しているかどうか。
   * @param byteCount 規定のバイト数
   * @return false = 以下, true = 超過
   */
  public boolean illegalByteCount(int byteCount){
    return value.getBytes(StandardCharsets.UTF_8).length > byteCount;
  }

  /**
   * 規定の単純な文字数を超過しているかどうか。
   * @param count 規定の単純な文字数
   * @return false = 以下, true = 超過
   */
  public boolean illegalWordCount(int count){
    return value.length() > count;
  }
}
