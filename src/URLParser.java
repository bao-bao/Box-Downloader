import java.util.ArrayList;
import java.util.List;

public class URLParser {
    static String videoFolder = "video/1080/";
    static String audioFolder = "audio/0/";
    List<List<String>> downloadUrlList;

    URLParser(String originUrl, int length) {
        if (length > 0) {
            downloadUrlList = new ArrayList<>();
            String frontPortion = originUrl.split("audio/0/1.m4s")[0];
            String endPortion = originUrl.split("audio/0/1.m4s")[1];
            int i = 1;
            List<String> fragment = new ArrayList<>();
            fragment.add(frontPortion + audioFolder +"init.m4s" + endPortion);
            fragment.add(frontPortion + videoFolder + "init.m4s" + endPortion);
            downloadUrlList.add(fragment);
            while (i <= length) {
                fragment = new ArrayList<>();
                fragment.add(frontPortion + audioFolder + i + ".m4s" + endPortion);
                fragment.add(frontPortion + videoFolder + i + ".m4s" + endPortion);
                downloadUrlList.add(fragment);
                i++;
            }
        }
    }



}
