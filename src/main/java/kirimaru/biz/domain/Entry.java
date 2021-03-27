package kirimaru.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entry implements Serializable {
  private List<Detail> detail;

  public List<ReturnHistory> flatHistory() {
    return Optional.ofNullable(detail).stream()
        .flatMap(Collection::stream)
        .map(Detail::getReturnHistory)
        .filter(Objects::nonNull)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }
}
