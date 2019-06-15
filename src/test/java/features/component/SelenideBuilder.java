package features.component;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideConfig;

public class SelenideBuilder {
    private static SelenideConfig config = new SelenideConfig();

    public static void builder() {
        // デフォルトで自分のページへ
        Configuration.baseUrl = "https://trpg-charactors.firebaseapp.com/";
        Configuration.headless = true;

        // 明示的にreportsFolderを表現しておく。
        Configuration.reportsFolder = config.reportsFolder();

        // cucumber側でスクリーンショット取りたいので、Selenideのデフォルトスクショ機能を無効にする。
        Configuration.screenshots = false;
    }
}
