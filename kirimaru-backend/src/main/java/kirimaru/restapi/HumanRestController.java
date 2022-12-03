package kirimaru.restapi;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import kirimaru.biz.domain.Animal;
import kirimaru.biz.domain.Human;
import kirimaru.restapi.converter.DtoConverter;
import kirimaru.restapi.converter.HumanDtoConverter;
import kirimaru.restapi.dto.HumanDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
