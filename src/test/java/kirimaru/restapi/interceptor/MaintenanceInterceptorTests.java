package kirimaru.restapi.interceptor;

import kirimaru.restapi.AnimalsRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MaintenanceInterceptorTests {
  private MockMvc mockMvc;
  private final String rootUrl = "/animals";

//  @Mock
//  WebRequestInterceptor webRequestInterceptor;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(new AnimalsRestController())
//        .addInterceptors(new MaintenanceInterceptor(webRequestInterceptor))
        .addInterceptors(new MaintenanceInterceptor())
        .build();
  }

  @Test
  void success() throws Exception {
    this.mockMvc.perform(get(rootUrl).param("piyo", "aiueo"))
        .andExpect(status().isOk())
        .andExpect(content().string("hogehoge"));
  }

}
