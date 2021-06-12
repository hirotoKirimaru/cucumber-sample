package kirimaru.biz.domain.executor;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    Status status = new Status();
    return status.increment();
  }
}
