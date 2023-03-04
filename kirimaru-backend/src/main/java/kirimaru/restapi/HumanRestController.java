package kirimaru.restapi;

import kirimaru.biz.domain.Human;
import kirimaru.restapi.converter.DtoConverter;
import kirimaru.restapi.dto.HumanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/humans")
@RequiredArgsConstructor
public class HumanRestController {
  private final DtoConverter<HumanDto, Human> converter;

  public ResponseEntity<Void> insert(HumanDto dto){
    var entity = converter.toEntity(dto);
    // 処理
    return ResponseEntity.ok().build();
  }

  public ResponseEntity<HumanDto> findAll(String condition){
    var dto = converter.toDto(new Human());
    // 処理
    return ResponseEntity.ok(dto);
  }
}
