package kirimaru.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureWebClient
@WebMvcTest(DefultServlet.class)
class DefultServletTests {
    @Autowired
    private MockMvc mockMvc;
    private final String rootUrl = "/default";

    @Disabled("なにもわからん")
    @Test
    void test_01() throws Exception {
        this.mockMvc.perform(get(rootUrl))
                .andExpect(status().is5xxServerError())
        ;
    }
}
