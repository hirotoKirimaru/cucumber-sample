package kirimaru.biz.domain.date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@RequiredArgsConstructor
public class SystemDateTimeResolverImpl implements SystemDateTimeResolver {
  private final OffsetDateTimeResolver offsetDateTimeResolver;


  public LocalDateTime now() {
    return LocalDateTime.from(offsetDateTimeResolver.now());
  }

}
