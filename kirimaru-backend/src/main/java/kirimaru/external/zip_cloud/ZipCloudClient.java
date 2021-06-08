package kirimaru.external.zip_cloud;

import kirimaru.external.zip_cloud.dto.ZipCloudDto;

public interface ZipCloudClient {
  ZipCloudDto getAddressByZipcode(String zipCode);
}
