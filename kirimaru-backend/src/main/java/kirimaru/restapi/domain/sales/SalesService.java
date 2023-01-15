package kirimaru.restapi.domain.sales;

import java.util.List;
import kirimaru.restapi.SalesRestController.SaleDto;

public interface SalesService {

  List<SaleDto> execute();
}
