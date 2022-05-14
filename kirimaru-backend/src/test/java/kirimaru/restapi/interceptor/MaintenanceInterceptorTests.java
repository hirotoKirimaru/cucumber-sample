package kirimaru.restapi.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import kirimaru.biz.domain.Animal;
import kirimaru.restapi.AnimalsRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

  @Test
  void success2() throws Exception {
    // #以後は無視されていることを確認する(そういうテストは書けていない）
    this.mockMvc.perform(get(rootUrl+ "?piyo=aiueo#:~:text=ほげほげ"))
        .andExpect(status().isOk())
        .andExpect(content().string("hogehoge"));
  }


  @Disabled("ここまでする必要はない")
  @Test
  void test_post() throws Exception {
    ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    JavaTimeModule jtm = new JavaTimeModule();
    jtm.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
    objectMapper.registerModule(jtm);

    Animal animal = new Animal("1", LocalDateTime.now());

    this.mockMvc.perform(
            post(rootUrl)
                .content(objectMapper.writeValueAsString(animal))  // リクエストボディを指定
                .contentType(MediaType.APPLICATION_JSON_VALUE) // ヘッダ指定
        )
        .andExpect(status().isServiceUnavailable());
  }

}
