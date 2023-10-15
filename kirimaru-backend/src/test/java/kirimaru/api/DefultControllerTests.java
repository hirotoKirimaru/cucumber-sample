package kirimaru.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

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
