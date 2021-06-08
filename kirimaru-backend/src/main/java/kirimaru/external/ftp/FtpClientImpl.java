package kirimaru.external.ftp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FtpClientImpl {
  private final FtpConfiguration ftpConfiguration;

  public void ftp(Path localRootPath, List<Path> paths) {

    try (var ftp = new FtpFileTransmitter(ftpConfiguration)) {
      for (Path path : paths) {
        transfer(ftp, localRootPath, path);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * ファイルを転送する。
   *
   * 1. MKDIRとCDを繰り返して、目的の構造をリモートに生成する
   * 2. ファイルを転送する。
   * 3. ホームディレクトリに戻る。(複数ファイル転送のために一度ホームに戻る)
   */
  private void transfer(FtpFileTransmitter ftp, Path localRootPath, Path path) throws IOException {
    ftp.ftpCreateDirectoryTree(Path.of(createRemoteDirectory(localRootPath, path)));
    ftp.putFileToPath(path);

    ftp.changeHomeDirectory();
  }

  /**
   * ローカルパスからリモートのディレクトリを生成する。
   *
   * @param localRootPath　C:\\tmp\transfers
   * @param localPath C:\\tmp\transfers\blog\2020\11\18\01.png
   * @return blog\2020\11\18
   */
  private String createRemoteDirectory(Path localRootPath, Path localPath) {
        // Absoluteにすると、C:\\とでてきてWinとLinuxでうまく変換できない。
//    String remoteDirectoryIncludeFileName = localPath.toAbsolutePath().toString().replace(localRootPath, "");
    String remoteDirectoryIncludeFileName = localPath.toString().replace(localRootPath.toString(), "");
    return remoteDirectoryIncludeFileName.replace(localPath.getFileName().toString(), "");
  }
}
