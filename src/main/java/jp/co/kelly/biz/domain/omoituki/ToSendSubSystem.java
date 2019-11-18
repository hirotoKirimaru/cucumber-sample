package jp.co.kelly.biz.domain.omoituki;

import java.util.List;

public interface ToSendSubSystem extends SubSystem {
    default void execute() {
        List<?> record = getRecord();
        for (Object o : record) {
            System.out.println(toCsv(o));
        }

//      try {
//        List<?> record = getRecord();
//        try (FileWriter fw = new FileWriter("");
//             BufferedWriter bw = new BufferedWriter(fw);
//        ) {
//          for (Object o : record) {
//            bw.write(toCsv(o));
//          }
//        }
//      } catch (Exception e){
//
//      }
    }
}