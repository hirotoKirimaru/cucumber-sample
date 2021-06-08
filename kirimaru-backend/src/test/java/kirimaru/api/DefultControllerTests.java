package kirimaru.api;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebClient
@WebMvcTest(value = DefultController.class)
class DefultControllerTests {
  @Autowired
  private MockMvc mockMvc;
  private final String rootUrl = "/index";

//  @Disabled("目的通りのエラーではあるのだが…？")
//  @Test
//  void test_01() throws Exception {
//    this.mockMvc.perform(get(rootUrl))
//        .andExpect(status().is5xxServerError())
//    ;
//  }
}
