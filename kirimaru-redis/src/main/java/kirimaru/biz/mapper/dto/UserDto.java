package kirimaru.biz.mapper.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String id;
    private String name;
}
