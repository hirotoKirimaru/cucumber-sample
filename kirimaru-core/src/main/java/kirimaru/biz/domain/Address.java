package kirimaru.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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

//  private String extracted(String moto, List<String> transferLogicList) {
//    String a = null;
//    List<String> array = new ArrayList<>();
//
//
//
////    for (String replace : List.of("-", "ー")) {
//      for (ATable replace : transferLogicList) {
//        moto = moto.replaceAll(replace.get変換元(), replace.get変換先());
//
//
//      address1.replaceAll(replace, "-")
//      address2.replaceAll(replace, "-");
//    }
//    return moto;
//  }
}
