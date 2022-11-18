package kirimaru.biz.mapper.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {

  String companyId;
  String name;

  @Builder.Default
  List<DepartmentDto> departmentList = List.of();
}
