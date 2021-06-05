package kirimaru.biz.domain.date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@RequiredArgsConstructor
public class SystemDateTimeResolverImpl implements SystemDateTimeResolver {
  private final OffsetDateTimeResolver offsetDateTimeResolver;


  public LocalDateTime now() {
    return LocalDateTime.from(offsetDateTimeResolver.now());
  }

}
