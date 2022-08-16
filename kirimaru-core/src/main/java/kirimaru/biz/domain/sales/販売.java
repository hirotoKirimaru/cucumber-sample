package kirimaru.biz.domain.sales;

import java.util.List;
import java.util.Objects;

public record 販売(
    List<仕入> 仕入れList,
    売上 売上

) {
}
