package jp.co.kelly.restapi.interceptor;

import io.restassured.module.mockmvc.internal.StandaloneMockMvcFactory;
import jp.co.kelly.restapi.AnimalsRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.request.WebRequestInterceptor;

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
