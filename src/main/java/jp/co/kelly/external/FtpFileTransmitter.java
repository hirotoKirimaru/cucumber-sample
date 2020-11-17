package jp.co.kelly.external;


import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Threadsafeではないので、DIコンテナへの登録はNG。
 */
@RequiredArgsConstructor
public class FtpFileTransmitter implements AutoCloseable {
  private final FtpConfiguration configuration;

  private FTPClient ftp;
  private String home;

  public void connect() throws IOException {
    ftp = new FTPClient();
    ftp.setDefaultTimeout(configuration.getDefaultTimeout() * 1000);
    ftp.setDataTimeout(configuration.getDataTimeout() * 1000);
    ftp.connect(configuration.getHost(), configuration.getPort());

    int reply = ftp.getReplyCode();
    if (!FTPReply.isPositiveCompletion(reply)) {
      throw new RuntimeException("接続できませんでした");
    }
    ftp.setSoTimeout(configuration.getSoTimeout() * 1000);

    try {
      ftp.login(configuration.getUsername(), configuration.getPassword());
      ftp.enterLocalPassiveMode();
      home = ftp.printWorkingDirectory();
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public void close() throws IOException {
    if (ftp == null || !ftp.isConnected()) {
      ftp = null;
      return;
    }

    try {
      ftp.disconnect();
    } finally {
      ftp = null;
    }
  }

  public void changeBinaryFileType() throws IOException {
    ftp.setFileType(FTP.BINARY_FILE_TYPE);
  }

  public void ftpCreateDirectoryTree(Path remoteDirectory) throws IOException {
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

    if (!alreadyExists){
      if (!ftp.makeDirectory(dir.toString())) {
        throw new RuntimeException("ディレクトリ作れなかった");
      }
    }

    if (!ftp.changeWorkingDirectory(dir.toString())) {
      throw new RuntimeException("cdできなかった");
    }
  }

  public void putFileToPath(Path path, String toString) throws IOException {
    ftp.storeFile(toString, Files.newInputStream(path));
    ftp.changeWorkingDirectory(home);
  }
}


