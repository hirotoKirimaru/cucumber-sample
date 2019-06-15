# 未来の自分へ
## 環境

- selenide
    - e2eテストを行うときの要
- cucumber
    - BDDを行うのに必須。JavaでBDDやるなら2019/06/15時点でもこれだと思うが、wrapperライブラリがあれば乗り換える。RubyならTurnip?

## やったこと
- build.gradle
    - selenideとcucumberの設定
    testImplementation 'com.codeborne:selenide:5.2.3'
    testImplementation 'io.cucumber:cucumber-java:4.3.1'
    testImplementation 'io.cucumber:cucumber-junit:4.3.1'
    - taskの追加
        - cucumberとcucumberNow
            - tagの glue でstepsファイルの場所を指定するので超重要。自分の環境に合わせてね。
            - cucumberNowでは now タグをついているものだけを動かす。
            - cucumberとcucumberNowのどちらとも、developing タグが付いている場合、実行しないようにしている。仕様だけとりあえず実装したい時に便利。

- cucumberの設定
    - cucumberにはbeforeとafterのアノテーションがあるので、事前処理、後処理を設定できる。
    - 今は、エラー時にシナリオ名のスクショを取るようにしている。

### できていないこと
- vueファイルのコンパイル。
    - 今後、vueは触っていきたいので、必須級。
    ただ、vueのコンパイル後にE2Eを行う必要があるので、正直かなり遅くなる。
    DB操作等もE2Eでチェックしたいことも考えると、nightwatch.js等ではカバーできないと判断。
    というか、そもそも、フロントエンド側でBDDをやるテストフレームワークが見つからなかった。
    あったけど、スター数が1桁のものしか見つからなかった…。