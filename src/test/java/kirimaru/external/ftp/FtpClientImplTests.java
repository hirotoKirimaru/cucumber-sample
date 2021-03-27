package kirimaru.external.ftp;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FtpClientImplTests {
  FtpClientImpl target;
  // 実行元はWin, MacわからないのでFile.speratorで判別すべきだが、転送先はUnixで間違いないので、固定したほうが良さそう。
  private static final Path TMP_ROOT_PATH = Path.of("tmp", "dummy");
  private static final Path EXPECTED_FILE_PATH = Path.of("20201116", "AIUEO");
  private static final String EXPECTED_FILE_ONE = "01.png";
  private static final String EXPECTED_FILE_TWO = "02.png";
  public static final String HOME = "/user";

  FakeFtpServer server = new FakeFtpServer();

  public FtpClientImplTests() {
    server.setServerControlPort(0);
    server.addUserAccount(new UserAccount("user", "pass", HOME));

    FileSystem fileSystem = new UnixFakeFileSystem();
    fileSystem.add(new DirectoryEntry(HOME));
    server.setFileSystem(fileSystem);
  }

  @BeforeEach
  void setup() {
    FileUtils.deleteQuietly(FileUtils.getFile(TMP_ROOT_PATH.toFile()));
    server.start();

    FtpConfiguration ftpConfiguration = new FtpConfiguration();
    ftpConfiguration.setUsername("user");
    ftpConfiguration.setPassword("pass");
    ftpConfiguration.setHost("localhost");
    ftpConfiguration.setPort(server.getServerControlPort());
    ftpConfiguration.setDefaultTimeout(3);
    ftpConfiguration.setSoTimeout(3);
    ftpConfiguration.setDataTimeout(3);

    target = new FtpClientImpl(ftpConfiguration);

  }

  @AfterEach
  void tearDoiwn() {
    server.stop();
    FileUtils.deleteQuietly(FileUtils.getFile(TMP_ROOT_PATH.toFile()));
  }

  @Test
  void test_01() throws IOException {
    Files.createDirectories(Paths.get(TMP_ROOT_PATH.toString(), EXPECTED_FILE_PATH.toString()));
    List<Path> paths = List.of(
        Files.createFile(Paths.get(TMP_ROOT_PATH.toString(), EXPECTED_FILE_PATH.toString(), EXPECTED_FILE_ONE))
    );
    target.ftp(TMP_ROOT_PATH, paths);

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(server.getFileSystem().exists(HOME + "/" + EXPECTED_FILE_PATH.toString())).isTrue();
    softly.assertThat(server.getFileSystem().exists(HOME + "/" + EXPECTED_FILE_PATH.toString() + "/" + EXPECTED_FILE_ONE)).isTrue();
    softly.assertThat(Files.exists(Paths.get(TMP_ROOT_PATH.toString()))).isTrue();
    softly.assertAll();
  }

  @Test
  void test_02() throws IOException {
    Files.createDirectories(Paths.get(TMP_ROOT_PATH.toString(), EXPECTED_FILE_PATH.toString()));
    List<Path> paths = List.of(
        Files.createFile(Paths.get(TMP_ROOT_PATH.toString(), EXPECTED_FILE_PATH.toString(), EXPECTED_FILE_ONE)),
        Files.createFile(Paths.get(TMP_ROOT_PATH.toString(), EXPECTED_FILE_PATH.toString(), EXPECTED_FILE_TWO))
    );
    target.ftp(TMP_ROOT_PATH, paths);

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(server.getFileSystem().exists(HOME + "/" + EXPECTED_FILE_PATH.toString())).isTrue();
    softly.assertThat(server.getFileSystem().exists(HOME + "/" + EXPECTED_FILE_PATH.toString() + "/" + EXPECTED_FILE_ONE)).isTrue();
    softly.assertThat(server.getFileSystem().exists(HOME + "/" + EXPECTED_FILE_PATH.toString() + "/" + EXPECTED_FILE_TWO)).isTrue();
    softly.assertThat(Files.exists(Paths.get(TMP_ROOT_PATH.toString()))).isTrue();
    softly.assertAll();
  }

}
