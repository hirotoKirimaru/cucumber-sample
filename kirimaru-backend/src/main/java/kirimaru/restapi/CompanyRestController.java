package kirimaru.restapi;

import kirimaru.biz.domain.Company;
import kirimaru.biz.domain.Human;
import kirimaru.biz.mapper.dto.CompanyDto;
import kirimaru.restapi.converter.DtoConverter;
import kirimaru.restapi.dto.HumanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyRestController {
  private final DtoConverter<CompanyDto, Company> converter;

  public ResponseEntity<Void> insert(CompanyDto dto){
    var entity = converter.toEntity(dto);
    // 処理
    return ResponseEntity.ok().build();
  }

  public ResponseEntity<CompanyDto> findAll(String condition){
    var dto = converter.toDto(new Company());
    // 処理
    return ResponseEntity.ok(dto);
  }
}
