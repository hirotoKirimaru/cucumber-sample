package jp.co.kelly.biz.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class DateConverter {
    public LocalDateTime now(){
        return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }

    public String toString(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
