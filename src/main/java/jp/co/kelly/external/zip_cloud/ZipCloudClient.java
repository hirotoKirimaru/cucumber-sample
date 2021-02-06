package jp.co.kelly.external.zip_cloud;

import jp.co.kelly.external.zip_cloud.dto.ZipCloudDto;

public interface ZipCloudClient {
  ZipCloudDto getAddressByZipcode(String zipCode);
}
