package kirimaru.biz.mapper.helper;

import kirimaru.biz.domain.date.DateTimeResolver;
import kirimaru.biz.domain.date.SystemDateTimeResolver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static kirimaru.helper.ApplicationContextAccessor.getBean;

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
