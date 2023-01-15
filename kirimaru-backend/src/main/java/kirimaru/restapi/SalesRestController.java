package kirimaru.restapi;

import java.util.List;
import javax.validation.Valid;
import kirimaru.restapi.domain.sales.SalesService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesRestController {
  private final SalesService service;

  @GetMapping(value = "")
  public ResponseEntity<SalesDto> findAll(@Valid Param param)  {

    return ResponseEntity.ok(new SalesDto(service.execute()));
  }

  @Data
  @AllArgsConstructor
  private static class Param {
  }

  @Data
  @AllArgsConstructor
  public static class SalesDto {
    private List<SaleDto> salesList;
  }

  @Data
  @AllArgsConstructor
  public static class SaleDto {
    private String id;
    private String name;
  }
}
