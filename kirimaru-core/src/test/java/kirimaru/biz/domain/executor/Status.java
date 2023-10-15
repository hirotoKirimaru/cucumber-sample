package kirimaru.biz.domain.executor;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Value;

@Value
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class Status {
  private final AtomicInteger atomicInteger;

  public Status() {
    this.atomicInteger = new AtomicInteger(0);
  }

  public int increment(){
    return atomicInteger.incrementAndGet();
  }

}
