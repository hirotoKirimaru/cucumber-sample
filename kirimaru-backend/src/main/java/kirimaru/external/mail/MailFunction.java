package kirimaru.external.mail;

public interface MailFunction {
  String FROM = "from@example.com";
  String CC = "cc@example.com";

  default void mail(String to){
    mail(to, FROM, CC);
  };
  default void mail(String to, String from){
    mail(to, from, CC);
  };
  void mail(String to, String from, String cc);

}
