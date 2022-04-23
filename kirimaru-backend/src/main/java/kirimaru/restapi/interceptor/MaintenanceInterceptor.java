package kirimaru.restapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
//@Component
//public class MaintenanceInterceptor extends WebRequestHandlerInterceptorAdapter {
public class MaintenanceInterceptor implements AsyncHandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

//    super.preHandle(request, response, handler);
//    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
//
//    if (now.isBefore(LocalDateTime.MAX)) {
//      log.error("メンテナンス中です");
//      return false;
//    }
    if (HttpMethod.GET.name().equals(request.getMethod())) {
      log.info("getメソッド");
      return true;
    }

    log.info("preHandle");
//    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
//    return false;
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable ModelAndView modelAndView) throws Exception {

    log.error("postHandle");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                              @Nullable Exception ex) throws Exception {
    log.error("afterCompletion");
    if (ex != null) {
      log.error(Arrays.toString(ex.getStackTrace()));
    }
  }


}
