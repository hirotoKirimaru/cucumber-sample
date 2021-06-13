package kirimaru.biz.mapper.helper;

import kirimaru.biz.domain.constant.CodeConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Configuration
public class MapperProperties {
  private String user = "kirimaru";
}

