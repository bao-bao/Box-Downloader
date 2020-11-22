import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;


public class PDFDownloader {
    public static void main(String [] args) throws Exception {
        String ustr = "https://dl.boxcloud.com/api/2.0/files/742808840945/content?preview=true&version=791338831745&access_token=1!MhzWYEeHNr08n93pY1hQfq-LwJmkriKz8kFYVDxUOY6FXGQtgXDp-onAhVyg5MIqc3AfdM4PfwbonV1xH3IwdpjS6Fi20QvpASGeSgt_UlLU9kxY6SEKqcJ01Oq7oqnV5b72pbm5aWkpGpiS7XT2rvNtmeBKJ6K26yLpx3vaeumWQsddIYfn_gSkgRmQoamBvV_nVQkrroTE5Lm3Aywnjz_trq0ENFyHIsqidmpA6GzN7rPZTDCAAUTSOpsxV6ntK7hPOyLe6h3kd_zUAH-qGxuTgt_0BBCe4v_WqDMuYgUKdgcmyEBRE04QSq0k7lN10sUTngOfpgw557HKzr3rrjC3HUN2R938o2f-QTKiTyx33XRvWQjIKGhbh2qDhdKaMnW2GbTOOODfbZvjnFYLw_CmxBbGZ_Basd64TNhpuJ3TqDm-vQKzvCWP1jADTjIb9UkkQ824MuPEJ90mWISCkSBPTVsqIx_VPKTzbFsmMKC-ToyS9G6oitB0DlroFd9MGq3hE6SBUBxpynmTE8LALcWHttPaZ6ebuawuzda7AI7lg-eEmaTcYJvPXRQlq3B4wSvkGvnT3kE6GlbdOndTTn9DDGlCtKcsCeIthnt9t-H33lN2kKgOvST0rOUrc35PJe8BY7IM8vAGqvaVZr59RMi2ZAYLXIaWvO4tIS_orq82382TyiWPGNYErtkr-1D_8VYuutHISyfjNjx-QSHhX-wnxEWpp1pTqMhvPlTkhRC4-6RGbw..&shared_link=https%3A%2F%2Fbuffalo.app.box.com%2Fs%2Fg6dajrj1w4fcigzcikul1pxwmwc4nxi4&box_client_name=box-content-preview&box_client_version=2.57.0&encoding=gzip";
        URL url = new URL(ustr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Accept-Encoding", "gzip");
        GZIPInputStream stream = new GZIPInputStream(con.getInputStream());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            int res = 0;
            byte[] buf = new byte[1024];
            while (res >= 0) {
                res = stream.read(buf, 0, buf.length);
                if (res > 0) {
                    output.write(buf, 0, res);
                }
            }
            File pdf = new File("hw11.pdf");
            FileOutputStream fos = new FileOutputStream(pdf);
            fos.write(output.toByteArray());
        } catch (IOException e) {
            System.out.println("Write Error");
        }
    }
}
