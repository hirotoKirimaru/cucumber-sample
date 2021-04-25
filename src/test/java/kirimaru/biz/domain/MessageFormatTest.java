package kirimaru.biz.domain;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


class MessageFormatTest {

  @Test
  void test_01() {
    //staticなメソッドを使う方法
    String template = "Hello! {0}さん。{1}も元気？";
    Object[] params = {"太郎","花子"};
    String message = MessageFormat.format(template, params);
    System.out.println(message);


    //インスタンスを生成する方法
    MessageFormat mf = new MessageFormat("エラーコード:{0} エラー理由：{1}");
    String[] params2 = {"E-112", "不正な文字列です。"};
    String[] params3 = {"E-113", "文字列が空です。"};
    String[] params4 = {"E-114", "セッションが不正です。"};
    System.out.println(mf.format(params2));
    System.out.println(mf.format(params3));
    System.out.println(mf.format(params4));


    //数字のフォーマット
    MessageFormat mfNo = new MessageFormat("数字そのまま{0} or カンマなし：{1,number,#}");
    Integer[] paramsNo = {123456,123456};
    System.out.println(mfNo.format(paramsNo));

    String str = String.format("%05d", 1);
    System.out.println(str);


    //0埋め(パディング)
    MessageFormat mfNo2 = new MessageFormat("0埋め数字{0,number,0000}");
    Integer[] paramsNo2 = {1};
    System.out.println(mfNo2.format(paramsNo2));


    //日付と時間の変換
    MessageFormat mfDay = new MessageFormat("こんにちは。今日は{0,date,yyyy年MM月dd日}、時刻は{0,time}です。数字だけにすると、{0,date,yyyyMMdd}、時刻は{0,time,HHMMSS}です。");
    Object[] paramsDay = {new Date(System.currentTimeMillis())};
    System.out.println(mfDay.format(paramsDay));

    Date date = new Date();
    String dateStr = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
    System.out.println(dateStr);
  }
}
