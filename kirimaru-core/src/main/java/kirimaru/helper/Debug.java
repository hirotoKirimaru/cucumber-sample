package kirimaru.helper;

public interface Debug {

  /**
   * 現在処理行を取得する
   * @return 現在処理行
   */
  default int getLineNo() {
//    System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
    return Thread.currentThread().getStackTrace()[2].getLineNumber();
  }

}
