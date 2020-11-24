# Box-Downloader

## Environment
  - Windows (Need some changes on calling ffmpeg for Linux and Mac)
  - Java 11 or later
  - [ffmpeg 4.3.1](https://ffmpeg.org/download.html) 
 
## How to Download
1. Set Path  
    In `Application.java`:  
      - Change `outFile` as the folder to store your video.
      > Please make sure the directory exist!
      - Change `ffmpegFile` as your ffmepg executable file path. 
      > If you are using Linux or Mac, the function calling ffmpeg should be changed.  
      > Please do that or contact me for another version.
      - Change `csvPath` as your .csv path. 

2. Build your download list  
   Your download list should be a .csv file. The .csv file should follow pattern like:  
  
      | File Name | Video Length | Request URL |
      | :---: | :---: | :---: |
      | Introduction.mp4 | 18 | https://... |

      - The `File Name` can be anything you want.  
      - The `Video Length` is the amount of seconds divided by 5, then round it up. For example, the `length` of a 2:43 video should be $(2\times60+43)\div5=33$. If you want the exact number, try to play the last 5-10 seconds of the video, then look at the number in the request list.   
      - The `Request URL` **MUST** be the URL contain a substring of `audio/0/1.m4s`. In most cases, this request is the first start with `1.m4s?`.  

      **Attention: DO NOT add any header in your csv, it should be pure**  
      **Attention: The URL copied from request header will expire uncertainly, please download once you copy them** 
      > An example of .csv file is also uploaded, named `url.csv`.

   The URL required can be found:
    - Open a video on Box with the inspection open (`F12` for Chrome).  
    - Find any request begin with `*.m4s`, click it.
    - In the request header, under `General` label, copy the whole `Request URL` which fits my requirement.  
    ![ITX4N_RQG7EBTQ781L9U3M0.png](https://i.loli.net/2020/11/24/5OPR3B9VQsJcYdg.png)


> If your video is broken, try to use a larger `length` for downloading.  
> For a `length` larger than expected, the progress bar may stop at middle, but that won't result in a broken video.

---
updated at 11/24/2020