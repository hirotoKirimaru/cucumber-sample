package kirimaru.biz.domain;

public class TableStand {

    void disp(String ja) {
        Season season = transSeason(ja);
        displaySeason(season);
    }

    Season transSeason(String ja) {
        return Season.findJa(ja);
    }

    void displaySeason(Season season){
        System.out.println(season.getJa() + "は英語で「" + season.getEn() + "」です。");
    }
}

enum Season {
    SPRING("春", "Spring"),
    SUMMER("夏", "Summer"),
    ;

    private String ja;
    private String en;

    Season(String ja, String en) {
        this.ja = ja;
        this.en = en;
    }

    public String getJa(){
        return this.ja;
    }

    public String getEn(){
        return this.en;
    }

    public static Season findJa(String ja) {
        for (Season season : Season.values()) {
            if (season.ja.equals(ja)) {
                return season;
            }
        }
        return null;
    }
}
