package jp.co.kelly.restapi;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(defultRestController.class)
class defultRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void success() throws Exception {
        this.mockMvc.perform(get("/").param("piyo", "aiueo"))
                .andExpect(status().isOk())
                .andExpect(content().string("hogehoge"));
    }

    @Test
    void runtimeException() throws Exception {
        this.mockMvc.perform(get("/").param("piyo", "runtime"))
                .andExpect(status().isConflict())
                .andExpect(content().string("runtimeException"));
    }

    @Test
    void hogeError() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));


    }

    @Nested
    public class paramValidate {
        @Autowired
        private MockMvc mockMvc;


    }
}
