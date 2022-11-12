package kirimaru.biz.mapper.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {

  String companyId;
  String name;

  List<DepartmentDto> departmentList;
}
