package kirimaru.external.create_csv;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class CsvCreateImplTests {

  @Test
  void test_create_utf_8() throws IOException {

    Path csvFile = Paths.get("utf8.csv");
    String[] csvData = "山田,山形,25\n山県,山梨,30\n".split("\n");

    // StandardCharsetsのパッケージ見てると分かるけど、
    // UTF_8のBOMはない
    // UTF-16LEのBOM, UTF-32BE_BOM, UTF-32LE_BOM ならある
    try (FileWriter fw = new FileWriter(csvFile.toString(), StandardCharsets.UTF_8)) {
      for (String csvDatum : csvData) {
        fw.append(csvDatum);
        fw.append("\n");
      }
      fw.flush();
    }

    Files.delete(csvFile);
  }

  @Test
  void test_create_utf8_bom() throws IOException {

    Path csvFile = Paths.get("utf8-bom.csv");
    String[] csvData = "山田,山形,25\n山県,山梨,30\n".split("\n");

    // StandardCharsetsのパッケージ見てると分かるけど、
    // UTF_8のBOMはない
    // UTF-16LEのBOM, UTF-32BE_BOM, UTF-32LE_BOM ならある
    try (FileOutputStream fos = new FileOutputStream(csvFile.toFile());
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
      osw.append("\uFEFF"); // BOM付与
      for (String csvDatum : csvData) {
        osw.append(csvDatum);
        osw.append("\n");
      }
      osw.flush();
    }

    Files.delete(csvFile);
  }
}