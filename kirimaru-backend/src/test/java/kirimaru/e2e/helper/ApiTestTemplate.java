package kirimaru.e2e.helper;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiTestTemplate {

  @Autowired
  TestRestTemplate restTemplate;

  @LocalServerPort
  String port;

  String CONTEXT_PATH = "http://localhost:%s/%s";

  protected ResponseEntity<String> patch(String path, String body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // ファイルアップロードの時。
//    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

    URI uri = UriComponentsBuilder.fromHttpUrl(CONTEXT_PATH.formatted(port, path))
        .buildAndExpand()
        .encode()
        .toUri();

    return restTemplate.exchange(
        uri, HttpMethod.PATCH, httpEntity, String.class
    );
  }

  protected String patch2(String path, String body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // ファイルアップロードの時。
//    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

    URI uri = UriComponentsBuilder.fromHttpUrl(CONTEXT_PATH.formatted(port, path))
        .buildAndExpand()
        .encode()
        .toUri();

    return restTemplate.patchForObject(
        uri, httpEntity, String.class
    );
  }

  protected ResponseEntity<String> post(String path, String body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // ファイルアップロードの時。
//    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

    URI uri = UriComponentsBuilder.fromHttpUrl(CONTEXT_PATH.formatted(port, path))
        .buildAndExpand()
        .encode()
        .toUri();

    return restTemplate.exchange(
        uri, HttpMethod.POST, httpEntity, String.class
    );
  }

  protected ResponseEntity<String> post2(String path, String body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // ファイルアップロードの時。
//    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

    URI uri = UriComponentsBuilder.fromHttpUrl(CONTEXT_PATH.formatted(port, path))
        .buildAndExpand()
        .encode()
        .toUri();

    return restTemplate.postForEntity(
        uri, httpEntity, String.class
    );
  }

}
