package jp.co.kelly.biz.domain.omoituki;

import java.util.List;

public interface SubSystem {
    List<?> getRecord();
    String toCsv(Object a);
}
