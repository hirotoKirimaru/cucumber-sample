package kirimaru.biz.domain.executor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.concurrent.atomic.AtomicInteger;

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
