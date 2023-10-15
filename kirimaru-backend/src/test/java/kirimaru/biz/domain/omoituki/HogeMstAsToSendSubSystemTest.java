package kirimaru.biz.domain.omoituki;

import kirimaru.biz.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
