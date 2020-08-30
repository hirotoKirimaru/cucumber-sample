package jp.co.kelly.biz.domain.book;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder", buildMethodName = "build")
public class CustomBuilderBook {
  private final Isbn id;
  private final int money;
  private final String author;

  public static class Builder {
    public CustomBuilderBook build() {
      validate();

      return new CustomBuilderBook(id, money, author);
    }

    public void validate() {
      if (money == 0) {
        throw new RuntimeException("エラー！");
      }
    }
  }


}
