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
public class DepartmentDto implements Serializable {
  String departmentId;
  String name;

  @Builder.Default
  List<UserDto> userList = List.of();
}
