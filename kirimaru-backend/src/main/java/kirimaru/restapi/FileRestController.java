package kirimaru.restapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Slf4j
@RestController
@RequestMapping("/files")
public class FileRestController {

  @PostMapping()
  public String uploadFile(@Valid @RequestBody UploadFile uploadFile) {
    System.out.println(uploadFile);

    System.out.println(
        new String(Base64.getDecoder().decode(
            uploadFile.getValue()
        ))
    );
    return "";
  }


  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName)
      throws Exception {
    Path path = Path.of("tmp", fileName);
    Resource resource = new PathResource(path);
    return ResponseEntity.ok()
        .contentType(getContentType(path))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  private MediaType getContentType(Path path) throws IOException {
    try {
      return MediaType.parseMediaType(Files.probeContentType(path));
    } catch (IOException e) {
      log.info("Could not determine file type.");
      return MediaType.APPLICATION_OCTET_STREAM;
    }
  }

  @Data
  @AllArgsConstructor
  protected static class UploadFile {

    private String value;
    private String name;
  }
}
