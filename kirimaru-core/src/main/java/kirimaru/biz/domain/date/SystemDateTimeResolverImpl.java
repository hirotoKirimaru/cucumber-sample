package kirimaru.biz.domain.date;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class SystemDateTimeResolverImpl implements SystemDateTimeResolver {
  private final OffsetDateTimeResolver offsetDateTimeResolver;


  public LocalDateTime now() {
    return LocalDateTime.from(offsetDateTimeResolver.now());
  }

}
