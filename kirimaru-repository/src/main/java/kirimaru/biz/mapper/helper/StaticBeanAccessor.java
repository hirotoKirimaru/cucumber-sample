package kirimaru.biz.mapper.helper;

import static kirimaru.helper.ApplicationContextAccessor.getBean;

import java.time.LocalDateTime;
import kirimaru.biz.domain.date.SystemDateTimeResolver;

public class StaticBeanAccessor {

  public static String getUser() {
    return getBean(MapperProperties.class).getUser();
  }

  private static LocalDateTime getNow() {
    return getBean(SystemDateTimeResolver.class).now();
  }

  public static String getNowString() {
    return getNow().toString();
  }
}
