package kirimaru.restapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import kirimaru.external.ftp.FtpClientImpl;
import kirimaru.external.ftp.FtpConfiguration;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;

@AutoConfigureWebClient
@WebMvcTest(FileRestController.class)
class FileRestControllerTests {

  @Autowired
  private MockMvc mockMvc;
  private final String rootUrl = "/files";
  private final Path path = Path.of("tmp/test.pdf");


  @BeforeEach
  void setup() throws IOException {
    FileUtils.copyToFile(
        getClass().getResourceAsStream("/kirimaru/test.pdf"),
        path.toFile()
    );
  }

  @AfterEach
  void tearDown() {
    FileUtils.deleteQuietly(
        path.toFile()
    );
  }

  @Test
  void test_01() throws Exception {
    String urlPath = "/downloadFile/test.pdf";

    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(rootUrl + urlPath))
        .andExpect(status().isOk())
        .andExpect(
            header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"test.pdf\""))
        .andReturn();

    byte[] body = mvcResult.getResponse().getContentAsByteArray();
    assertThat(body).isEqualTo(Files.readAllBytes(path));
  }


}