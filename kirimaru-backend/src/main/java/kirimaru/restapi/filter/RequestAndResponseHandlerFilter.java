package kirimaru.restapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@RequiredArgsConstructor
public class RequestAndResponseHandlerFilter extends OncePerRequestFilter {

  public static final String FILTER_CONFIGURATION_PREFIX = "kirimaru.request-and-response-handler-filter";

  public interface RequestHandler {

    void handle(String body, HttpServletRequest request);
  }

  public interface ResponseHandler {

    void handle(String body, HttpServletResponse response);
  }

  private final Configuration configuration;
  private final List<RequestHandler> requestHandlers;
  private final List<ResponseHandler> responseHandlers;

  @Override
  protected void initFilterBean() {
    if (hasNoHandlers()) {
      log.warn("No Handlers");
    }
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (hasNoHandlers() || !configuration.isTargetRequest(request)) {
      doFilter(request, response, filterChain);
      return;
    }

    var requestWrapper = wrap(request);
    var responseWrapper = wrap(response);

    try {
      doFilter(requestWrapper, responseWrapper, filterChain);
    } finally {
      handle(requestWrapper);
      handle(responseWrapper);
      responseWrapper.copyBodyToResponse();
    }
  }

  private boolean hasNoHandlers() {
    return requestHandlers.isEmpty() && responseHandlers.isEmpty();
  }

  private ContentCachingRequestWrapper wrap(HttpServletRequest request) {
    if (request instanceof ContentCachingRequestWrapper wrapper) {
      return wrapper;
    }

    return new ContentCachingRequestWrapper(request);
  }

  private ContentCachingResponseWrapper wrap(HttpServletResponse response) {
    if (response instanceof ContentCachingResponseWrapper wrapper) {
      return wrapper;
    }

    return new ContentCachingResponseWrapper(response);
  }

  private void handle(ContentCachingRequestWrapper request) throws IOException {
    if (requestHandlers.isEmpty()) {
      return;
    }

    String body = new String(request.getContentAsByteArray(), request.getCharacterEncoding());

    requestHandlers.forEach(h -> h.handle(body, request));
  }

  private void handle(ContentCachingResponseWrapper response) throws IOException {
    if (responseHandlers.isEmpty()) {
      return;
    }

    String body = new String(response.getContentAsByteArray(), response.getCharacterEncoding());

    responseHandlers.forEach(h -> h.handle(body, response));
  }

  private void readBodyToTheEnd(ContentCachingRequestWrapper request) throws IOException {
    ServletInputStream is = request.getInputStream();
    if (is.isFinished()) {
      return;
    }

    while (is.read() != -1) {
      // ignore
    }
  }

//  @ConfigurationProperties(FILTER_CONFIGURATION_PREFIX)
  public static class Configuration {

    @Setter
    private Set<String> excludePaths = Collections.emptySet();

    boolean isTargetRequest(HttpServletRequest request) {
      return !excludePaths.contains(request.getServletPath());
    }
  }
}
