package swlee.logiclist.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import swlee.logiclist.utils.S3UploaderService;

import java.io.IOException;
@Slf4j
@RequiredArgsConstructor
@Controller
public class S3UploaderController {
    private final S3UploaderService s3UploaderService;

    @GetMapping("/image")
    public String image() {
        return "image-upload";
    }
//
//    @GetMapping("/video")
//    public String video() {
//        return "video-upload";
//    }

    @PostMapping("/image-upload")
    @ResponseBody
    public String imageUpload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        log.info("imageUpload IN");
        String result = s3UploaderService.upload(multipartFile, "logiclist", "image");
        //JSON Response
        Gson gson = new Gson();
        // Json key, value 추가
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", result);
        // JsonObject를 Json 문자열로 변환
        String jsonStr = gson.toJson(jsonObject);
        // 생성된 Json 문자열 출력
        log.info("JSONSTR::{}",jsonStr);
        return jsonStr;

    }
    @PostMapping("image-delete")
    @ResponseBody
    public String delete_image(@RequestParam("data") String fileName){
        s3UploaderService.removeS3File("image/"+fileName,"logiclist");

        return "success";
    }
}