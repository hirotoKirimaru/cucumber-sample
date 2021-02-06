package jp.co.kelly.external.zip_cloud;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import jp.co.kelly.external.operation.RestOperationFactory;
import jp.co.kelly.external.operation.RestTemplateInterceptor;
import jp.co.kelly.external.zip_cloud.dto.ZipAddressDto;
import jp.co.kelly.external.zip_cloud.dto.ZipCloudDto;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestOperations;
import ru.lanwen.wiremock.ext.WiremockResolver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(WiremockResolver.class)
@Disabled("WebClientわからない")
class ZipCloudClientImplTests {

  ZipCloudClient target;

  private WireMockServer server;

  ZipCloudClientImplTests(@WiremockResolver.Wiremock WireMockServer server) {
    this.server = server;
  }

  @BeforeEach
  void setup() {
    ZipCloudClientProperties properties = new ZipCloudClientProperties();
    properties.setSchema("http");
    properties.setHost("localhost");
    properties.setPort(server.port());
    properties.setPath("/api/search");

//    RestOperationFactory restOperationFactory = new RestOperationFactory(restTemplateBuilder, new RestTemplateInterceptor());
//    RestOperations restOperations = restOperationFactory.createRestOperations(properties);

    target = new ZipCloudClientImpl(properties);
  }

  @Test
  void test_01() {
    // GIVEN
    // Language=JSON
    String responseBody =
        """
            {
              "message":null,
              "results": [{
                "address1":"神奈川県",
                "address2" : "厚木市",
                "address3" : "中町",
                "kana1" : "ｶﾅｶﾞﾜｹﾝ",
                "kana2" : "ｱﾂｷﾞｼ",
                "kana3" : "ﾅｶﾁｮｳ",
                "prefcode" : "14",
                "zipcode" : "2430018"
              }],
              "status": 200
            }""";

    server.stubFor(
        get("/api/search?zipcode=2430018").willReturn(
            aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "text/plain")
                .withBody(responseBody)
        )
    );


    ZipCloudDto expected = ZipCloudDto.builder()
        .message(null)
        .results(List.of(
            ZipAddressDto.builder()
                .address1("神奈川県")
                .address2("厚木市")
                .address3("中町")
                .kana1("ｶﾅｶﾞﾜｹﾝ")
                .kana2("ｱﾂｷﾞｼ")
                .kana3("ﾅｶﾁｮｳ")
                .prefcode("14")
                .zipcode("2430018")
                .build()
        ))
        .status("200")
        .build();

    ZipCloudDto actual = target.getAddressByZipcode("2430018");

    assertThat(actual).isEqualTo(expected);

    server.verify(1, getRequestedFor(urlEqualTo("/api/search?zipcode=2430018")));
  }

  @Test
  void test_02() {
    // Language=JSON
    String responseBody = """
        {
          "message": "パラメータ「郵便番号」の桁数が不正です。",
          "status": 400
        }""";

    server.stubFor(
        get("/api/search?zipcode=XXX").willReturn(
            aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "text/plain")
                .withBody(responseBody)
        )
    );


    ZipCloudDto expected = ZipCloudDto.builder()
        .message("パラメータ「郵便番号」の桁数が不正です。")
        .status("400")
        .build();

    ZipCloudDto actual = target.getAddressByZipcode("XXX");

    assertThat(actual).isEqualTo(expected);
    server.verify(1, getRequestedFor(urlEqualTo("/api/search?zipcode=XXX")));

  }

  @Disabled("今回やらないようにする")
  @DisplayName("ファイルから読み込むようにするだけ")
  @Nested
  class BodyJson {

    @Test
    void test_01() {
      // GIVEN
      String responseBody = readfile(Paths.get("external/zipCloud/01/request.json"));
      server.stubFor(
          get("/api/search?zipcode=2430018").willReturn(
              aResponse()
                  .withStatus(200)
                  .withHeader("Content-Type", "text/plain")
                  .withBody(responseBody)
          )
      );


      ZipCloudDto expected = ZipCloudDto.builder()
          .message(null)
          .results(List.of(
              ZipAddressDto.builder()
                  .address1("神奈川県")
                  .address2("厚木市")
                  .address3("中町")
                  .kana1("ｶﾅｶﾞﾜｹﾝ")
                  .kana2("ｱﾂｷﾞｼ")
                  .kana3("ﾅｶﾁｮｳ")
                  .prefcode("14")
                  .zipcode("2430018")
                  .build()
          ))
          .status("200")
          .build();

      ZipCloudDto actual = target.getAddressByZipcode("2430018");

      assertThat(actual).isEqualTo(expected);

      server.verify(1, getRequestedFor(urlEqualTo("/api/search?zipcode=2430018")));
    }
  }

  /**
   * getResourceAsStreamを使う！
   */
  private String readfile(Path path) {
    String responseBody = "";
    String name = "/" + path.toString();
    try (InputStream input = BodyJson.class.getResourceAsStream(name)) {
      responseBody = IOUtils.toString(input, StandardCharsets.UTF_8.toString());
    } catch (IOException e) {
      fail(e);
    }
    return responseBody;
  }

  @Disabled("毎回実行すると迷惑なので、基本はDisabledにする")
  @Nested
  class Production {
    @BeforeEach
    void setup() {
      ZipCloudClientProperties properties = new ZipCloudClientProperties();
      properties.setSchema("https");
      properties.setHost("zipcloud.ibsnet.co.jp");
      properties.setPort(0);
      properties.setPath("/api/search");

      RestOperationFactory restOperationFactory = new RestOperationFactory(restTemplateBuilder, new RestTemplateInterceptor());
      RestOperations restOperations = restOperationFactory.createRestOperations(properties);

//      target = new ZipCloudClientImpl(properties, restOperations);
      target = new ZipCloudClientImpl(properties);
    }

    @Test
    void test_01() {
      ZipCloudDto expected = ZipCloudDto.builder()
          .message(null)
          .results(List.of(
              ZipAddressDto.builder()
                  .address1("神奈川県")
                  .address2("厚木市")
                  .address3("中町")
                  .kana1("ｶﾅｶﾞﾜｹﾝ")
                  .kana2("ｱﾂｷﾞｼ")
                  .kana3("ﾅｶﾁｮｳ")
                  .prefcode("14")
                  .zipcode("2430018")
                  .build()
          ))
          .status("200")
          .build();

      ZipCloudDto actual = target.getAddressByZipcode("2430018");

      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_02() {
      ZipCloudDto expected = ZipCloudDto.builder()
          .message("パラメータ「郵便番号」の桁数が不正です。")
          .status("400")
          .build();

      ZipCloudDto actual = target.getAddressByZipcode("XXX");

      assertThat(actual).isEqualTo(expected);
    }
  }
}
