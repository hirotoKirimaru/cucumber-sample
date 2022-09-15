package kirimaru.helper;

public class DebugUtil {

  public static int getLineNo() {
    return Thread.currentThread().getStackTrace()[2].getLineNumber();
  }

}
