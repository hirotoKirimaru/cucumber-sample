package jp.co.kelly.external;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Threadsafeではないので、DIコンテナへの登録はNG。
 */
public class FtpFileTransmitter implements AutoCloseable {
  private final FtpConfiguration configuration;
  private final FTPClient ftp;
  private final String home;

  public FtpFileTransmitter(FtpConfiguration configuration) throws IOException{
    this.configuration = configuration;
    this.ftp = new FTPClient();
    connect();
    this.home = ftp.printWorkingDirectory();
  }

  private void connect() throws IOException {
    ftp.setDefaultTimeout(configuration.getDefaultTimeout() * 1000);
    ftp.setDataTimeout(configuration.getDataTimeout() * 1000);
    ftp.connect(configuration.getHost(), configuration.getPort());

    int reply = ftp.getReplyCode();
    if (!FTPReply.isPositiveCompletion(reply)) {
      throw new RuntimeException("サーバに接続できませんでした");
    }
    ftp.setSoTimeout(configuration.getSoTimeout() * 1000);

    if (!ftp.login(configuration.getUsername(), configuration.getPassword())) {
      throw new RuntimeException("ログインできませんでした");
    }

    ftp.enterLocalPassiveMode();
    ftp.setFileType(FTP.BINARY_FILE_TYPE);
  }

  // ftp = nullにした方がいい…？
  @Override
  public void close() throws IOException {
    if (ftp == null || !ftp.isConnected()) {
//      ftp = null;
      return;
    }

//    try {
    ftp.disconnect();
//    } finally {
//      ftp = null;
//    }
  }

  public void ftpCreateDirectoryTree(Path remoteDirectory) {
    final Iterator<Path> iterator = remoteDirectory.iterator();

    iterator.forEachRemaining(
        dir -> {
          try {
            makeDirectoryAndChange(dir);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
    );
  }

  private void makeDirectoryAndChange(Path dir) throws IOException {
    final boolean alreadyExists = Arrays.stream(ftp.listDirectories()).anyMatch(
        a -> a.getName().equals(dir.getFileName().toString())
    );

    if (!alreadyExists) {
      if (!ftp.makeDirectory(dir.toString())) {
        throw new RuntimeException("ディレクトリ作れなかった");
      }
    }

    if (!ftp.changeWorkingDirectory(dir.toString())) {
      throw new RuntimeException("cdできなかった");
    }
  }

  public void putFileToPath(Path path) throws IOException {
    ftp.storeFile(path.getFileName().toString(), Files.newInputStream(path));
  }

  public void changeHomeDirectory() throws IOException {
    ftp.changeWorkingDirectory(home);
  }
}


