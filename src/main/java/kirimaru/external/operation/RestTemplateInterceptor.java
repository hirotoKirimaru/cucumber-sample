package kirimaru.external.operation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    ClientHttpResponse response = execution.execute(request, body);
    if (log.isDebugEnabled()) {
      try {
        response = new BufferingClientHttpResponseWrapper(response);

        log.debug("""
                RestTemplate Requests: URI={},\s
                Headers={},\s
                Body={}""",
            request.getURI(),
            request.getHeaders(),
            new String(body));

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
          String line = bufferedReader.readLine();
          while (line != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = bufferedReader.readLine();
          }

          log.debug("""
                  RestTemplate Response: StatusCode={} {}\s
                  Headers={},\s
                  Body={}""",
              response.getStatusCode(),
              response.getStatusText(),
              response.getHeaders(),
              stringBuilder.toString()
          );
        }

      } catch (IOException e) {
        log.debug("Error：", e);
      }
    }

    return response;
  }

  private class BufferingClientHttpResponseWrapper implements ClientHttpResponse {
    private final ClientHttpResponse response;
    private byte[] body;

    public BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
      this.response = response;
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
      return this.response.getStatusCode();
    }

    @Override
    public int getRawStatusCode() throws IOException {
      return this.response.getRawStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
      return this.response.getStatusText();
    }

    @Override
    public void close() {
      this.response.close();
    }

    @Override
    public InputStream getBody() throws IOException {
      if (this.body == null) {
        this.body = StreamUtils.copyToByteArray(this.response.getBody());
      }
      return new ByteArrayInputStream(this.body);
    }

    @Override
    public HttpHeaders getHeaders() {
      return this.response.getHeaders();
    }
  }
}
