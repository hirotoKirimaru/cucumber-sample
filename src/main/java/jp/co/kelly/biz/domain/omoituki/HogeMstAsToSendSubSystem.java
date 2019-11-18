package jp.co.kelly.biz.domain.omoituki;

import jp.co.kelly.biz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class HogeMstAsToSendSubSystem implements ToSendSubSystem {
    private final QuestionRepository repository;

    void createCsv() {
        execute();
    }

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