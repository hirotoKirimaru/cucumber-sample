package kirimaru.biz.mapper.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDepartmentDto implements Serializable {
  String companyId;
  String departmentId;
}
