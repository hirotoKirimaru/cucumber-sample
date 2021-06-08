Feature: ログイン機能
  Scenario: トップページが表示される
    When topページに遷移する
    Then "top"ページを表示する

  @developing
  Scenario: ログインする
    Given topページに遷移する
    When ログインボタンを押す

  @developing
  Scenario: ログアウトする
    Given topページに遷移する
    When ログアウトボタンを押す




