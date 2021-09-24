package kirimaru.restapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;

@AutoConfigureWebClient
@WebMvcTest(FileRestController.class)
class FileRestControllerTests {

  @Autowired
  private MockMvc mockMvc;
  private final String rootUrl = "/files";
  private static final Path TMP_ROOT_PATH = Path.of("tmp", "dummy");
  private static final String EXPECTED_FILE_ONE = "test.pdf";

  @BeforeEach
  void setup() {
    FileUtils.deleteQuietly(FileUtils.getFile(TMP_ROOT_PATH.toFile()));
  }

  @AfterEach
  void tearDown() {
    FileUtils.deleteQuietly(FileUtils.getFile(TMP_ROOT_PATH.toFile()));
  }

  @Test
  void test_01() throws Exception {
    Files.createDirectories(Paths.get(TMP_ROOT_PATH.toString()));
    List<Path> paths = List.of(
        Files.createFile(Paths.get(TMP_ROOT_PATH.toString(), EXPECTED_FILE_ONE))
    );

    String path = "/downloadFile/test.pdf";

    this.mockMvc.perform(MockMvcRequestBuilders.get(rootUrl + path))
        .andExpect(status().isOk())
        .andExpect(
            header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"test.pdf\""))
    ;
  }


}