package kirimaru.restapi.converter;

import kirimaru.biz.domain.Company;
import kirimaru.biz.mapper.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoConverter implements DtoConverter<CompanyDto, Company> {

  @Override
  public Company toEntity(CompanyDto dto) {
    return null;
  }

  @Override
  public CompanyDto toDto(Company entity) {
    return null;
  }
}