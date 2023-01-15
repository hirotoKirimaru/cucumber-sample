package kirimaru.restapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import kirimaru.restapi.SalesRestController.SaleDto;
import kirimaru.restapi.domain.sales.SalesService;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureWebClient
@WebMvcTest(SalesRestController.class)
@TestPropertySource(properties = {
    "spring.jackson.default-property-inclusion=NON_EMPTY",
//    "spring.jackson.default-property-inclusion=ALWAYS",
})
class SalesRestControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean(answer = Answers.CALLS_REAL_METHODS)
  private SalesService service;
  private final String rootUrl = "/sales";

  @Test
  void success() throws Exception {
    String expected= """
        {
            "salesList": []
        }
        """;

    MvcResult result = this.mockMvc.perform(get(rootUrl))
        .andExpect(status().isOk())
        .andReturn();
//        .andExpect(content().json(actual));
    System.out.println(result.getResponse().getContentAsString());
    JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
  }

  @Test
  void success2() throws Exception {

    when(service.execute()).thenReturn(List.of(
        new SaleDto(null, "DDD"),
        new SaleDto(null, "CCC")
    ));

    // LANGUAGE=JSON
    String expected = """
{
  "salesList": [
    {
      "name": "DDD"
    },
    {
      "name": "CCC"
    }
  ]
}
       """;

    var result = this.mockMvc.perform(get(rootUrl))
        .andExpect(status().isOk())
        // .andExpect(content().json(expected)) // 上手く比較できなかった
        .andReturn();

//    System.out.println(resultActions);
//    assertThat(result.getResponse().getContentAsString()).;
    System.out.println(result.getResponse().getContentAsString());
    JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
  }


  @Test
  void success3() throws Exception {
    // GIVEN
    when(service.execute()).thenReturn(Collections.singletonList(null));

    // LANGUAGE=JSON
    String expected = """
{
  "salesList": [null]
}
       """;

    var result = this.mockMvc.perform(get(rootUrl))
        .andExpect(status().isOk())
        // .andExpect(content().json(expected)) // 上手く比較できなかった
        .andReturn();

//    System.out.println(resultActions);
//    assertThat(result.getResponse().getContentAsString()).;
    System.out.println(result.getResponse().getContentAsString());
    JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
  }

}