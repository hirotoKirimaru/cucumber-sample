package kirimaru.biz.domain.conflict.register;

import kirimaru.DemoApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * デフォルトだとDI名がコンフリクトする。
 * だから、ComponentScanのnameGeneratorで加工する
 *
 * {@link DemoApplication}
 */
@Component
@RequiredArgsConstructor
public class ConflictComponent {
//  @Qualifier("kirimaru.biz.domain.conflict.register.ConflictService2")
  @Qualifier("ConflictService")
  private final ConflictService conflictService;
}
