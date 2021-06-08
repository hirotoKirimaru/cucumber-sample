package kirimaru.biz.domain.date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Data
@Component
@RequiredArgsConstructor
public class OffsetDateTimeResolverImpl implements OffsetDateTimeResolver {
  @Override
  public OffsetDateTime now() {
    return OffsetDateTimeResolver.super.now();
  }
}
