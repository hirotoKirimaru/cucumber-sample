import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AbstractConfig {
  private static final String ROOT_PATH = "src/main/resources/";
  private static final String INIT_FILE_PATH = ROOT_PATH + "selenium.properties";
  private static final Properties properties;

  static {
    properties = new Properties();
    try {
      properties.load(Files.newBufferedReader(Paths.get(INIT_FILE_PATH), StandardCharsets.UTF_8));
    } catch (Exception e) {
      throw new RuntimeException(
          String.format("ファイルの読み込みに失敗しました。ファイル名:%s", INIT_FILE_PATH),
          e
      );
    }
  }

  /**
   * プロパティ値を取得する
   *
   * @param key キー
   * @return 値
   */
  public static String getProperty(final String key) {
    return getProperty(key, "");
  }

  /**
   * プロパティ値を取得する
   *
   * @param key          キー
   * @param defaultValue デフォルト値
   * @return キーが存在しない場合、デフォルト値
   * 存在する場合、値
   */
  public static String getProperty(final String key, final String defaultValue) {
    return properties.getProperty(key, defaultValue);
  }
}
