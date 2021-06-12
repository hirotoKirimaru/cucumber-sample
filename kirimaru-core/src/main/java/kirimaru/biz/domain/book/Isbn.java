package kirimaru.biz.domain.book;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Isbn {
  private final String isbn;

  private static final String PREFIX_ISBN = "978";


  public Isbn(String code) {
    if (!(code.length() == 10 || code.length() == 13)) throw new RuntimeException("ISBNの桁数が正しくない");
    String isbn1 = convert10to13(code);
    // ISBNではない可能性がある。2列目バーコードを読み取った可能性。
    if (!PREFIX_ISBN.equals(isbn1.substring(0, 3))) {
      throw new RuntimeException();
    }
    this.isbn = isbn1;
  }

  /**
   * 10桁のisbnだったら、13桁のISBNに変換
   *
   * @param isbn
   * @return 13桁のISBN
   */
  private String convert10to13(String isbn) {
    if (isbn.length() == 10) {
      return toIsbn13(isbn);
    }
    return isbn;
  }

  /**
   * ISBNを10桁から13桁に変更する。
   * 変更方法としては、
   * 1. ISBN10の1桁目を除く。(10桁から9桁)
   * 2. 除いたISBNの頭に”978”を追加する
   * 3. 奇数桁 * 1 + 偶数桁 * 3を足したものを10で割り、追加する。10の場合は追加しない。
   *
   * @param isbn ISBN10
   * @return ISBN13
   */
  private String toIsbn13(String isbn) {
    String isbn13 = PREFIX_ISBN + isbn.substring(0, 9);

    int multiple;
    int checkDigit = 0;
    for (int i = 0; i < isbn13.length(); i++) {
      multiple = i % 2 == 0 ? 1 : 3;
      checkDigit += Integer.parseInt(String.valueOf(isbn13.charAt(i))) * multiple;
    }
    checkDigit = 10 - (checkDigit % 10);

    if (checkDigit == 10) {
      return isbn13 + 0;
    } else {
      return isbn13 + checkDigit;
    }
  }
}
