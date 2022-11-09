package kirimaru.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.Null;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

@Slf4j
public class Debugs {

  public static int getLineNo() {
    return Thread.currentThread().getStackTrace()[2].getLineNumber();
  }

  public static String debugJson(@Nullable Object object, String... ignore) {
    if (object == null) {
      return "";
    }
    if (object instanceof String str) {
      return str;
    }

    try {
      ObjectMapper om = new ObjectMapper();
      // Nullは出力しない設定
      om.setSerializationInclusion(JsonInclude.Include.NON_NULL);

      // Serializeできないのはちゃんと設定する
      JavaTimeModule javaTimeModule = new JavaTimeModule();
//      javaTimeModule.addDeserializer(
//          LocalDateTime.class,
//          new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"))
//      );

      javaTimeModule.addSerializer(
          LocalDateTime.class,
          new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"))
      );
      om.registerModule(javaTimeModule);

      SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
          .serializeAllExcept(ignore);
      FilterProvider filters = new SimpleFilterProvider()
          .addFilter("myFilter", theFilter);

      log.debug(om.writer(filters).writeValueAsString(object));
      return om.writer(filters).writeValueAsString(object);
      // 自分で頑張ってJSON作るときにはこっち。親クラス名が欲しいときはこっちで作るといいかも。
      // ただ、Stringの"がエスケープされてすごい見づらい。
//      ObjectNode objectNode = om.createObjectNode();
//      objectNode.put(object.getClass().getSimpleName(), om.writeValueAsString(object));
//      System.out.println(objectNode.toString());
//      System.out.println(objectNode.toPrettyString()); // 改行されて綺麗だけど、ログの中に出したいから逆に邪魔。

//      System.out.println(om.writeValueAsString(object));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("うまく変換できない！", e);
    }
  }

}
