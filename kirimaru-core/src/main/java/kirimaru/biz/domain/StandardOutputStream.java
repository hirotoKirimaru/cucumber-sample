package kirimaru.biz.domain;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

public class StandardOutputStream extends PrintStream {
  private BufferedReader br = new BufferedReader(new StringReader(""));

  public StandardOutputStream() {
    super(new ByteArrayOutputStream());
  }

  /**
   * 1行分の文字列を読み込む
   *
   * @return 改行を含まない文字。終端の場合はnull
   */
  public String readLine() {
    String line = "";
    try {
      if ((line = br.readLine()) != null) return line;
      br = new BufferedReader(new StringReader(out.toString()));
      ((ByteArrayOutputStream) out).reset();
      return br.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
