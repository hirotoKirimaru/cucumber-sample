package jp.co.kelly.external;


import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Threadsafeではないので、DIコンテナへの登録はNG。
 */
@RequiredArgsConstructor
public class FtpFileTransmitter implements AutoCloseable{
  private final FtpConfiguration configuration;

  private FTPClient ftp;

  public void connect() throws IOException {
    ftp = new FTPClient();
    ftp.setDefaultTimeout(configuration.getDefaultTimeout() * 1000);
    ftp.setDataTimeout(configuration.getDataTimeout() * 1000);
    ftp.connect(configuration.getHost(), configuration.getPort());

    int reply = ftp.getReplyCode();
    if (FTPReply.isPositiveCompletion(reply)) {
      ftp.disconnect();
      throw new RuntimeException("接続できませんでした");
    }
    ftp.setSoTimeout(configuration.getSoTimeout() * 1000);

    try{
      ftp.login(configuration.getUsername(), configuration.getPassword());
      ftp.enterLocalPassiveMode();
    } catch(Exception e){
      this.close();
      throw e;
    }
  }

  @Override
  public void close() throws IOException{
    if (ftp == null || !ftp.isConnected()){
      ftp = null;
      return;
    }

    try{
      ftp.disconnect();
    }finally {
      ftp = null;
    }
  }

  public void changeBinaryFileType() throws IOException {
    ftp.setFileType(FTP.BINARY_FILE_TYPE);
  }
}


