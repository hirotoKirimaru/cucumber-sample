package jp.co.kelly.external;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FtpClientImplTests {
  FtpClientImpl target;
  private static final String separator = File.separator;
  private static final String TMP_ROOT_PATH = separator + "tmp" + separator + "dummy";
  private static final String EXPECTED_FILE_PATH = separator + "20201116" + separator + "AIUEO" + separator;
  private static final String EXPECTED_FILE_ONE = "01.png";
  private static final String EXPECTED_FILE_TWO = "02.png";


  FakeFtpServer server = new FakeFtpServer();

  public FtpClientImplTests() {
    server.setServerControlPort(9082);
    server.addUserAccount(new UserAccount("user", "pass", "/"));

    FileSystem fileSystem = new UnixFakeFileSystem();
    fileSystem.add(new DirectoryEntry("/"));
    server.setFileSystem(fileSystem);
  }

  @BeforeEach
  void setup() {
    FileUtils.deleteQuietly(FileUtils.getFile(TMP_ROOT_PATH));
    server.start();

    FtpConfiguration ftpConfiguration = new FtpConfiguration();
    ftpConfiguration.setUsername("user");
    ftpConfiguration.setPassword("pass");
    ftpConfiguration.setHost("localhost");
    ftpConfiguration.setPort(9082);
    ftpConfiguration.setDefaultTimeout(3);
    ftpConfiguration.setSoTimeout(3);
    ftpConfiguration.setDataTimeout(3);

    target = new FtpClientImpl(ftpConfiguration);

  }

  @AfterEach
  void tearDoiwn() {
    server.stop();
    FileUtils.deleteQuietly(FileUtils.getFile(TMP_ROOT_PATH));
  }


  @Test
  void test_01() throws IOException {
    Files.createDirectories(Paths.get(TMP_ROOT_PATH + EXPECTED_FILE_PATH));
    List<Path> paths = List.of(
        Files.createFile(Paths.get(TMP_ROOT_PATH + EXPECTED_FILE_PATH + EXPECTED_FILE_ONE))
    );
    target.ftp(TMP_ROOT_PATH, paths);


    assertThat(server.getFileSystem().exists(EXPECTED_FILE_PATH)).isTrue();
    assertThat(server.getFileSystem().exists(EXPECTED_FILE_PATH + EXPECTED_FILE_ONE)).isTrue();
    assertThat(Files.exists(Paths.get(TMP_ROOT_PATH))).isTrue();
  }

}
