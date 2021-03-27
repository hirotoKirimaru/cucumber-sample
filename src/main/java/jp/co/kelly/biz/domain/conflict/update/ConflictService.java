package jp.co.kelly.biz.domain.conflict.update;

import org.springframework.stereotype.Service;

/**
 * デフォルトだとDI名がコンフリクトする。
 * だから、ComponentScanのnameGeneratorで加工する
 *
 * {@link jp.co.kelly.DemoApplication}
 */
@Service
public class ConflictService {
}
