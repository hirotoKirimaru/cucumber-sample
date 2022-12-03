package kirimaru.restapi.converter;

public interface DtoConverter<T, R> {
  R toEntity(T dto);
  T toDto(R entity);
}
