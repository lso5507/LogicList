package swlee.logiclist.PureTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created by swlee on 2022-10-05.
 * Description : 사용하지 않는 AWS s3 Url 필터링 후 AWS S3 내 제거
 */
@Slf4j
public class BoardUpdateTest {
    @Test
    void test(){
            String content="![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/1.bmp)![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/258dab66-f537-42f3-aec6-f1e79a5c44f0474db22210e-10e4-433a-a374-83b03a1d0dd5.bmp)\n" +
                    "test입니다\n";
            String newContent ="![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/258dab66-f537-42f3-aec6-f1e79a5c44f0474db22210e-10e4-433a-a374-83b03a1d0dd5.bmp)\n" +
                    "tes22t입니다\n";
        String substring = content.substring(content.indexOf("logiclist.s3")-8, content.indexOf(")"));
//        System.out.println("substring = " + substring);
//            String[] result = imageFilter(content, newContent);
//            // result for
//            for (String s : result) {
//                log.info("s = {}",s);
//            }
        String data="https://logiclist.s3.ap-northeast-2.amazonaws.com/image/4562be21-d31c-4f6e-919d-450f9bcb4d4579217e47-98ae-4fe0-9901-1b0806871358.png";
        data=data.substring(data.indexOf("image"));
        System.out.println("data = " + data);

    }

    private String[] imageFilter(String content, String newContent) {
        String[] split = content.split("!\\[\\]");
        String[] newSplit = newContent.split("!\\[\\]");
        String[] result = split;
        //newSplit for i
        for (int i = 0; i < newSplit.length; i++) {
            // 빈배열 제거
            if(newSplit[i].equals("")){
                continue;
            }
            //split[i] subString(indexof(http),indexof(")")
            newSplit[i]  = newSplit[i].substring(newSplit[i].indexOf("https://"), newSplit[i].indexOf(")"));
        }
        //split for i
        for (int i = 0; i < split.length; i++) {
            // 빈배열 제거
            if(split[i].equals("")){
                continue;
            }
            //split[i] subString(indexof(http),indexof(")")
            split[i]  = split[i].substring(split[i].indexOf("https://"), split[i].indexOf(")"));
        }
        // split Value Not match newSplit Value
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < newSplit.length; j++) {
                if(result[i].equals(newSplit[j])){
                    result[i]="";
                }
            }
        }
        return  result;



    }
}
