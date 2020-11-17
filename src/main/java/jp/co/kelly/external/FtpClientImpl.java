package jp.co.kelly.external;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FtpClientImpl {
  private final FtpConfiguration ftpConfiguration;
  public FtpClientImpl(FtpConfiguration ftpConfiguration){
    this.ftpConfiguration = ftpConfiguration;
  }

  public void ftp(Path tmpRootPath, List<Path> paths) {

    try(FtpFileTransmitter ftp = new FtpFileTransmitter(ftpConfiguration)){

      ftp.connect();
      ftp.changeBinaryFileType();
      for (Path path : paths) {
        createAndMoveRemoteDirectory(tmpRootPath ,path, ftp);
        transferFiles(path, ftp);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private void transferFiles(Path path, FtpFileTransmitter ftp) throws IOException {
    if (!Files.exists(path)) {
      throw new RuntimeException("ファイルが無い");
    }
    ftp.putFileToPath(path, path.getFileName().toString());
  }

  private void createAndMoveRemoteDirectory(Path s, Path path, FtpFileTransmitter ftp) throws IOException {
    if (!Files.exists(path)){
      throw new RuntimeException("");
    }

    String remoteFileName = path.getFileName().toString();

//    String remoteDirectory = path.toAbsolutePath().toString().replace(s, "");
    String remoteDirectory = path.toString().replace(s.toString(), "");
    remoteDirectory = remoteDirectory.replace(remoteFileName, "");
    ftp.ftpCreateDirectoryTree(Path.of(remoteDirectory));
  }
}
