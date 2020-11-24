import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class VideoDownloader extends Thread {
    String outFile;
    String ffmpegFile;
    static String tempPath = "temp/";

    VideoDownloader(String outFile, String ffmpegFile) {
        this.outFile = outFile;
        this.ffmpegFile = ffmpegFile;
        File temp = new File(tempPath);
        if (temp.exists() && !temp.isDirectory()) {
            throw new RuntimeException("temp is not a directory");
        } else if (!temp.exists()) {
            if (!temp.mkdir()) {
                throw new RuntimeException("Can't make temp directory");
            }
        }
    }

    public void runFFmpeg(List<List<String>> urlList) {
        int length = urlList.size();
        deleteTempFiles();
        long start = System.currentTimeMillis();
        ProgressBar bar = new ProgressBar(length-1, 30);
        for (int i = 0; i < length; i++) {
            bar.showBarByPoint(i);
            try {
                download(urlList.get(i).get(0), TYPE.AUDIO, false);
                download(urlList.get(i).get(1), TYPE.VIDEO, i == length-1);
            } catch (IOException e) {
                bar.finish();
                System.out.println("Connection Error/File not exist");
                break;
            }
        }
        bar.finish();
        ffmpegUtil(tempPath + "video.m4s", tempPath + "audio.m4s", outFile, ffmpegFile);
        System.out.println("Success\ntime spent:" + (System.currentTimeMillis() - start) / 1000.0 + " s");
    }

    private void download(String urlStr, TYPE type, boolean isLastTime) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        InputStream stream = con.getInputStream();
        toByteArray(stream, output);
        stream.close();

        String filepath = type == TYPE.VIDEO ? tempPath + "video.m4s" : tempPath + "audio.m4s";
        try {
            File fragment = new File(filepath);
            if (!fragment.exists()) {
                if (!fragment.createNewFile()) {
                    System.out.println("Can't create file");
                }
            }
            FileOutputStream fos = new FileOutputStream(fragment, true);
            fos.write(output.toByteArray());
            if (isLastTime) {
                fos.close();
                output.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file");
        } catch (IOException e) {
            System.out.println("Write Error");
        }
    }

    private static void deleteTempFiles() {
        File temp = new File(tempPath);
        if (temp.exists() && temp.isDirectory()) {
            String[] filenames = temp.list((dir, name) -> name.endsWith(".m4s"));
            if (filenames != null) {
                for (String name : filenames) {
                    File file = new File(tempPath + "/" + name);
                    if (!file.delete()) {
                        try {
                            FileWriter fileWriter = new FileWriter(file);
                            fileWriter.write("");
                            fileWriter.flush();
                            fileWriter.close();
                        } catch (IOException e) {
                            System.out.println("Reset " + name + " failed, next video will collapse");
                        }
                    }

                }
            }
        }
    }

    private static void toByteArray(InputStream stream, ByteArrayOutputStream output) throws IOException {
        int res = 0;
        byte[] buf = new byte[1024];
        while (res >= 0) {
            res = stream.read(buf, 0, buf.length);
            if (res > 0) {
                output.write(buf, 0, res);
            }
        }
    }

    private static void ffmpegUtil(String videoFile, String audioFile, String outFile, String FFmpeg) {
        if (videoFile != null && audioFile != null && outFile != null && FFmpeg != null) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            List<String> command = new ArrayList<>();
            command.add(FFmpeg);
            command.add("-i");
            command.add(videoFile);
            command.add("-i");
            command.add(audioFile);
            command.add("-c");
            command.add("copy");
            command.add(outFile);

            processBuilder.command(command)
                    .redirectError(new File(tempPath + "log.txt"))
                    .redirectOutput(new File(tempPath + "log.txt"));

            try {
                Process process = processBuilder.start();
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
