import java.util.List;

public class Application {
    public static void main(String[] args) {
        // where your ffmpeg locate
        String ffmpegFile = "D:\\software\\ffmpeg\\bin\\ffmpeg.exe";
        // where to store videos (this is a directory! NOT forgot the '\\'! Eg, "C:\\output\\")
        String outFolder = "D:\\classes\\EAS501\\output\\";
        // csv file path
        String csvPath = "csvs/vectors_url.csv";

        // ***DO NOT change anything below, neither other code*** //
        CSVReader fileReader = new CSVReader(csvPath);
        for(List<String> file : fileReader.records) {
            VideoDownloader downloader = new VideoDownloader(outFolder + file.get(0), ffmpegFile);
            URLParser parser = new URLParser(file.get(2), Integer.parseInt(file.get(1)));
            System.out.println("Start Downloading " + file.get(0));
            downloader.runFFmpeg(parser.downloadUrlList);
        }
    }
}
