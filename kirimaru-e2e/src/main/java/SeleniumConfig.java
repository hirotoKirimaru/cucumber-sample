import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SeleniumConfig {
  private static final String ROOT_PATH = "src/main/resources/";
  private static final Path INIT_FILE_PATH = Path.of(ROOT_PATH, "selenium.properties");
  private static final Properties properties;

  static {
    properties = new Properties();
    try {
      properties.load(Files.newBufferedReader(INIT_FILE_PATH, StandardCharsets.UTF_8));
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

  public static String getBaseUrl() {
    return properties.getProperty(Key._0.getValue());
  }

  public static String getUsername() {
    return properties.getProperty(Key._1.getValue());
  }

  public static String getPassword() {
    return properties.getProperty(Key._2.getValue());
  }

  public static String getCompany() {
    return properties.getProperty(Key._3.getValue());
  }


  public enum Key {
    _0("baseUrl"),
    _1("username"),
    _2("password"),
    _3("company");

    private final String value;

    Key(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}
