package kirimaru.restapi.converter;

import kirimaru.biz.domain.Human;
import kirimaru.restapi.dto.HumanDto;
import org.springframework.stereotype.Component;

@Component
public class HumanDtoConverter implements DtoConverter<HumanDto, Human> {

  @Override
  public Human toEntity(HumanDto dto) {
    return null;
  }

  @Override
  public HumanDto toDto(Human entity) {
    return null;
  }
}
