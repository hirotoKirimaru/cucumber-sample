package kirimaru.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.net.URI;

@Getter
@Setter
@Slf4j
@ToString
public abstract class ExternalProperties {
  private String host;
  private String protocol;
  private String port;
  private String endpoint;
  private String timeout;

  @ToString.Exclude // ログに出さない
  private String basicUser;
  @ToString.Exclude // ログに出さない
  private String basicPassword;

  @ToString.Include(name = "rootUrl") // デフォルトはメソッド名になる
  public URI getUri() {
    return URI.create(protocol + "://" + host + ":" + port + "/" + endpoint);
  }

  // getterと同名ならログが出る？デモでない。
  public boolean isHoge() {
    return false;
  }


  @PostConstruct
  public void display() {
    log.info("********************");
    log.info(this.toString());
    log.info("********************");
  }

  // 起動しなくなるので、PostConstructはしない
//  @PostConstruct
  public void validate() {
    if (host == null) {
      throw new RuntimeException("設定されていない！");
    }
  }
}
