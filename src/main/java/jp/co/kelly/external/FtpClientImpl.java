package jp.co.kelly.external;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public class FtpClientImpl {
  private final FtpConfiguration ftpConfiguration;
  public FtpClientImpl(FtpConfiguration ftpConfiguration){
    this.ftpConfiguration = ftpConfiguration;
  }

  public void ftp(String tmpRootPath, List<Path> paths) {

    try(FtpFileTransmitter ftp = new FtpFileTransmitter(ftpConfiguration)){

      ftp.connect();
      ftp.changeBinaryFileType();


    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
