package kirimaru.restapi.domain.sales;

import java.util.List;
import kirimaru.restapi.SalesRestController.SaleDto;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService {

  @Override
  public List<SaleDto> execute() {
    return List.of();
  }
}
