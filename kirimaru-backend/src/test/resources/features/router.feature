Feature: ヘッダーの画面遷移を確認する

  @now
  Scenario Outline: トップページが表示される
    Given topページに遷移する
    When "<ヘッダ>"を選択する
    Then "<ページ>"を表示する
    Examples:
      | ヘッダ       | ページ        |
      | Home      | home       |
      | Game      | games      |
      | Charactor | charactors |





