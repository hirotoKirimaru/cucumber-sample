package jp.co.kelly.external.zip_cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZipAddressDto {
  private String address1;
  private String address2;
  private String address3;
  private String kana1;
  private String kana2;
  private String kana3;
  private String prefcode;
  private String zipcode;
}
