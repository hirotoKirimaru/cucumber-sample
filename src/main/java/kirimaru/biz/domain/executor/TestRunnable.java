package kirimaru.biz.domain.executor;

public class TestRunnable implements Runnable {

  public void run() {
    // スレッドIDを出力する
    System.out.println(Thread.currentThread().getId());
    Status status = new Status();
    System.out.println(status.increment());
  }
}
