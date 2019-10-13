package jp.co.kelly.biz.domain;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class LogCaptor {
    private final DateConverter dateConverter;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void execute() {
        log.debug("ログ={}", "start");
        dateConverter.toString(LocalDateTime.of(2019,1,1,1,1));
        log.debug("ログ={}", "end");
    }

}

