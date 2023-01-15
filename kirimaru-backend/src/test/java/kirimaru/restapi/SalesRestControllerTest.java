package kirimaru.restapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kirimaru.restapi.domain.sales.SalesService;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureWebClient
@WebMvcTest(SalesRestController.class)
class SalesRestControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean(answer = Answers.CALLS_REAL_METHODS)
  private SalesService service;
  private final String rootUrl = "/sales";

  @Test
  void success() throws Exception {
    String actual = """
        {
        
        }
        """;


    this.mockMvc.perform(get(rootUrl))
        .andExpect(status().isOk())
        .andExpect(content().json(actual, false));
  }

}