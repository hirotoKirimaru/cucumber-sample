package kirimaru.biz.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Animal implements Serializable {
  private String id;
  private transient LocalDateTime registerDate;
}
