public class Application {
    public static void main(String[] args) {
        String outFile = "D:\\classes\\EAS501\\output.mp4";     // where the output file locate (the directory must exist)
        String ffmpegFile = "D:\\software\\ffmpeg\\bin\\ffmpeg.exe";    // where your ffmpeg locate

        // args:
        // token, id, vid, and sharedLink can be found in the request header
        // length is the total amount of seconds divided by 5 (round up)
        String token = "1!YhowyYH4peOA8MG09vnUqYOBYPzU_X2dzzrxFVdQLM2TCNEfOgMHp-T6Ionfzdow7ufDhos990SxU-vjc47HGY3t-3J_8cHCMwoer0AgIB8A7aNqYmdykKE6zewnWZDsIGu_UjLKCQ6nf64IEjF0tvdFiS0yoGrZe5oUdPxqJK5F08zzOUNxVH6IMrM-LYm0fUFDpgfGlMKfcVRWkMRxHqb9NcutoAjR08Z0BRku-jWR_mQM2Imm9zXizGmjGu8ab0WFbHUPkwrP3R_C2UEfA9yMp-uRai72p4jz9tDhld4MHDgwkmr1imAqaGJ7RR-CCh4TB9efc9x329Hd2Fcnr1cWezwJXCsa4yzPMKaUUDc60f7UCR3lSrwvrizec75ED-neKEx6DZ3hHtGhqa3suWypEvw8agCI2eEW8mURlaiqytC0YrUU7jKaWnbj-G7FZucUVnqh1HGwCB00W6Og_YOsgegYN-xm0FPveDAXVfRzsMan63eCRjkJUzE7aC3XD9IG-ew2YgTxG-nY9VhSTHST0n0-LpTv5__59MQeBVpTthDkD-dmqWRo51yquBM1wxXVErigXVRibfy5KzFf84x0GoOFeW-oViSs8k-oa6K6GtB1Kqjddg67m3BgKQ4p6VFF3VjRsOdMvm1eHivkdQZuOzDWL7M08HF2l_l0NyIaZ969S-qVWmzSok_n43zpVZ9Ok1Jt24ruf-MzxmpxWRh3N8EWp1nHf4BW99MoEbU6mJbMNFA0FKUMvA..";
        String id = "692301872269";
        String vid = "735259876669";
        String sharedLink = "https://buffalo.app.box.com/s/ehkzxf7ubqdd0zj3ev9mtgyvie3u5h3j";
        int length = 30;

        // Dont Touch other code if you dont know what will cause.
        VideoDownloader downloader = new VideoDownloader(outFile, ffmpegFile);
        downloader.runFFmpeg(token, id, vid, sharedLink, length);
    }
}
