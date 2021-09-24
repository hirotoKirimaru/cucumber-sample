package kirimaru.restapi;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Validated
@RestController
@RequestMapping("/files")
public class FileRestController {

  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request)
      throws Exception {
//    // Load file as Resource
//    Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//    // Try to determine file's content type
//    String contentType = null;
//    try {
    Resource resource = new UrlResource(Path.of("tmp", "dummy", fileName).toUri());
//      Resource resource = Paths.get(URI.create("")).toFile();
//
//
//      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//    } catch (IOException ex) {
//      logger.info("Could not determine file type.");
//    }
//
//    // Fallback to the default content type if type could not be determined
//    if(contentType == null) {
//      contentType = "application/octet-stream";
//    }

    return ResponseEntity.ok()
//        .contentType(MediaType.parseMediaType(contentType))
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }
}
