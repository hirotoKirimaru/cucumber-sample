package kirimaru.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Address implements Serializable {
  private String address1;
  private String address2;

  public String computeAddress1() {
//    String c = address;
//    String c = address1.replaceAll("ー", "-");

//    String 変換先1 = extracted("変換元");
//    String 変換先2 = extracted("変換元");
//    String 変換先3 = extracted("変換元");
//    String 変換先4 = extracted("変換元");

    return address1.replaceAll("[-ー‐‑–—―−ｰ]", "-");
//    return address1.replaceAll("[" + "-" + "ー" + "]", "-");
  }

  static class MasterCode_0001{
    String henkanmoto;
  }

  static class MasterCode_0022{
    String henkansaki;
  }

//  public String extracted(String address, List<MasterCode_0001> transferLogicList, MasterCode_0022 saki) {
    public String extracted(String address, List<String> transferLogicList) {
//    String a = null;
//    List<String> array = new ArrayList<>();
//    String a = this.address1;
//    String b = null;
//
//      for (ATable replace : transferLogicList) {
//        address = address.replaceAll(replace.get変換元(), replace.get変換先());
//      }
//
//    for (String replace : List.of("-", "ー")) {
    for (String replace : transferLogicList) {
//      for (ATable replace : transferLogicList) {
//        if (b == null) {
//          b = a.replaceAll(replace.get変換元(), replace.get変換先());
//        } else {
//          b = b.replaceAll(replace.get変換元(), replace.get変換先());
//        }
//
//      address1.replaceAll(replace, "-")
//      address2.replaceAll(replace, "-");
      address = address.replaceAll(replace, "-");
    }
    return address;
  }
}
