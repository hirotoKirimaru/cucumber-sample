package kirimaru.external.zip_cloud.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZipCloudDto {
  private String message;
  private List<ZipAddressDto> results;
  private String status;
}
