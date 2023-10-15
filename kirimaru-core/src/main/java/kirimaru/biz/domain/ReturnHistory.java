package kirimaru.biz.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnHistory implements Serializable {
  private String id;
  private LocalDateTime date;
  private Gorilla gorilla;
  private Animal animal;

  public ReturnHistory(String id, LocalDateTime date) {
    this.id = id;
    this.date = date;
  }
}
