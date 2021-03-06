package jp.co.kelly.restapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
    log.info("preHandle");
//    throw new RuntimeException("Error");

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
