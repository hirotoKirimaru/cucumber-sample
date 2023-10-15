package kirimaru.biz.domain.date;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class OffsetDateTimeResolverImpl implements OffsetDateTimeResolver {
  @Override
  public OffsetDateTime now() {
    return OffsetDateTimeResolver.super.now();
  }
}
