package kirimaru.biz.mapper;

import kirimaru.biz.mapper.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisMapper extends CrudRepository<UserDto, String> {

}
