package kirimaru.biz.domain.date;

import java.time.LocalDateTime;

public interface SystemDateTimeResolver extends DateTimeResolver {

  LocalDateTime now();
}
