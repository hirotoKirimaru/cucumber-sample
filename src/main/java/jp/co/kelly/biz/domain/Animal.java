package jp.co.kelly.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Animal implements Serializable {
  private String id;
  private transient LocalDateTime registerDate;
}
