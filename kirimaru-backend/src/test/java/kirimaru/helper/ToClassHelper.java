package kirimaru.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import kirimaru.biz.domain.Human;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ToClassHelper {
  ObjectMapper objectMapper = new ObjectMapper();

  <T> T fromJson(InputStream input, Class<T> clazz) {
    try {
      return objectMapper.readValue(input, clazz);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("何か失敗した", e);
    }
  }

  <T> T fromJson(Path path, Class<T> clazz) {
    File file = path.toFile();
    try (FileInputStream input = new FileInputStream(file)) {

      String json = IOUtils.toString(input, StandardCharsets.UTF_8);
      return fromJson(json, clazz);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("何か失敗した", e);
    }
  }

  <T> T fromJson(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException("Json変換できませんでした", e);
    }
  }
}
