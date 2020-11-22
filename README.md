# Box-Downloader

## Environment
  - Windows (Some change is needed for Linux and Mac)
  - Java 11 or later
  - [ffmpeg 4.3.1](https://ffmpeg.org/download.html) 
 
## How to Download
1. Set Path  
    In `Application.java`:  
      - Change `outFile` as the location to store your video.
      > Please make sure the directory exist!
      - Change `ffmpegFile` as your ffmepg excutable file path. 
      > If you are using Linux or Mac, the function calling ffmpeg should be change.  
      > Please do that or contact me for another version.
2. Set Download Parameters  
    - Open a video on Box with the inspection open (`F12` for Chrome).  
    - Find any request begin with `*.m4s`, click it.
    - In the request header, under `General` label, from `Request URL`, extract `id`, `vid`, `access_token`, and `shared_link`.  
    Expample:  
    ![_Z8F7SC_UDW81_W_7PT2G_3.png](https://i.loli.net/2020/11/22/aZLzcWmStquKJe4.png)

    In `Application.java`:
      - Paste `id`, `vid`, `token`, and `sharedLink` as you find.
      - Set `length` as the amount of seconds divided by 5, then round it up. For example, the `length` of a 2:43 video should be $(2\times60+43)\div5=33$.
      - Run the `main` funciton
      - Wait for result

> If your video is broken, try to use a larger `length` for download.  
> For a `length` larger than expected, the progress bar may stop at middle, but that won't result in a broken video.