package jp.co.kelly.biz.domain.omoituki;

import jp.co.kelly.biz.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HogeMstAsToSendSubSystemTest {
    HogeMstAsToSendSubSystem target;
    @Mock
    QuestionRepository repository;

    @BeforeEach
    void setup() {
       target = new HogeMstAsToSendSubSystem(repository);
    }

    @Test
    void constructor() {
        target.execute();
    }
}