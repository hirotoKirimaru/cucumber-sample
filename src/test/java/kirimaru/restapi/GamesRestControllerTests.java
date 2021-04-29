package kirimaru.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebClient
@WebMvcTest(GamesRestController.class)
class GamesRestControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private final String rootUrl = "/games";

    @Test
    void success() throws Exception {
        this.mockMvc.perform(get(rootUrl).param("piyo", "aiueo"))
                .andExpect(status().isOk())
                .andExpect(content().string("hogehoge"));
    }

    @Test
    void hogeError() throws Exception {
        this.mockMvc.perform(get(rootUrl).param("piyo", "12345678901234567890"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("gameException"));
    }
}
