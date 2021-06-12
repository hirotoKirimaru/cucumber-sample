package kirimaru.biz.domain.omoituki;

import kirimaru.biz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class HogeMstAsToSendSubSystem implements ToSendSubSystem {
    private final QuestionRepository repository;

    @Override
    public List<HogeMst> getRecord() {
        return Arrays.asList(
                HogeMst.builder()
                        .name("123")
                        .build(),
                HogeMst.builder()
                        .name("456")
                        .build()
        );
    }

    @Override
    public String toCsv(Object a) {
        if (!(a instanceof HogeMst)) {
            throw new RuntimeException();
        }
        HogeMst b = (HogeMst) a;
        return b.name;
    }

}
