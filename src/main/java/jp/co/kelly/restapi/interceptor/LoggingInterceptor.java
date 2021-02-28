package jp.co.kelly.restapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoggingInterceptor extends WebRequestHandlerInterceptorAdapter {
  /**
   * Create a new WebRequestHandlerInterceptorAdapter for the given WebRequestInterceptor.
   *
   * @param requestInterceptor the WebRequestInterceptor to wrap
   */
  public LoggingInterceptor(WebRequestInterceptor requestInterceptor) {
    super(requestInterceptor);
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    super.preHandle(request, response, handler);

    log.info(">>> {} {} [{}] [{}", request.getMethod(), request.getRequestURI(),
        getRemoteAddr(request), getControllerMethod(handler));

    return true;
  }

  private String getRemoteAddr(HttpServletRequest request) {
    String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
    if (ipFromHeader != null && ipFromHeader.length() > 0) {
      return ipFromHeader;
    }
    return request.getRemoteAddr();
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                              @Nullable Exception ex) throws Exception {
    super.afterCompletion(request, response, handler, ex);

    if (ex != null) {
      log.error("[Unhandled Error]{}: {}", ex.getClass(), ex.getMessage(), ex);
    }

    log.info("<<<{} [{}]", response.getStatus(), getControllerMethod(handler));
  }

  private String getControllerMethod(Object handler) {
    if (!(handler instanceof HandlerMethod)) {
      return "unknown";
    }
    HandlerMethod method = (HandlerMethod) handler;
    return method.getBeanType().getSimpleName() + "#" + method.getMethod().getName() + "()";
  }

}
