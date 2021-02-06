package jp.co.kelly.external.zip_cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZipCloudDto {
  private String message;
  private List<ZipAddressDto> results;
  private String status;
}
