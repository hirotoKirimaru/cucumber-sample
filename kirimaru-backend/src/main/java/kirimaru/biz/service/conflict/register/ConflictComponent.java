package kirimaru.biz.service.conflict.register;

import kirimaru.DemoApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
