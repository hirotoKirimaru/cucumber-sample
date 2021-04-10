package kirimaru.biz.domain.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Service {
  public int getInt(){
    ExecutorService exec = Executors.newCachedThreadPool();

    List<Future<Integer>> list = new ArrayList<Future<Integer>>();
    for (int i = 0; i < 5; i++) {
      Future<Integer> submit = exec.submit(new TestCallable());
      list.add(submit);
    }

    for (Future<Integer> integerFuture : list) {
      try {
        System.out.println(integerFuture.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }

    return 0;
  }


}
