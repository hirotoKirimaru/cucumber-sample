package kirimaru.biz.domain.conflict.register;

import kirimaru.DemoApplication;
import org.springframework.stereotype.Service;

/**
 * デフォルトだとDI名がコンフリクトする。
 * だから、ComponentScanのnameGeneratorで加工する
 *
 * {@link DemoApplication}
 */
@Service
public class ConflictService {
}
