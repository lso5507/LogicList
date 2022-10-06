package swlee.logiclist.utils;

import com.amazonaws.AmazonServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class S3UploaderServiceTest {


    @Test
    void removeS3File() {
        String oldContent="content::myLogicList![](https://logiclist.s3.ap-northeast-2.amazonaws.com/image/258dab66-f537-42f3-aec6-f1e79a5c44f0474db10e-10e4-433a-a374-83b03a1d0dd5.bmp)";
        String newContent="content::";
        removeS3File(oldContent,newContent);

    }
    public boolean removeS3File(String old_content,String new_content){

        String[] result = imageFilter(old_content, new_content);
        if(result==null){
            //삭제할 이미지가 없음
            return  true;
        }
        // for문 result
        for (int i = 0; i < result.length; i++) {
            if(result[i].equals("")||result[i] == null){
                continue;
            }
            try {
                log.info("파일삭제");
            } catch (Exception e) {
                log.info("File delete fail");
                return false;
            }
        }

        return  true;
    }
    private String customSplit(String split) {
        return split.substring(split.indexOf("https://"), split.indexOf(")"));
    }

    /**
     * AWS S3에 저장된 이미지 삭제
     */
    private String[] imageFilter(String content, String newContent) {
        // content에 image Url이 없을경우 기존 이미지가 없으므로 삭제할 이미지가 없음
        if(!isContains(content)) {
            return null;
        }
        // newContent가 image Url이 없을경우 새로운 이미지가 없으므로 기존 이미지 삭제
        else if(!isContains(newContent)) {
            String[] split = content.split("\\[\\]");
            for (int i = 0; i < split.length; i++) {
                if(!isContains(split[i])){
                    split[i] = "";
                    continue;
                }
                split[i]= customSplit(split[i]);
            }
            return split;

        }

        String[] split = content.split("!\\[\\]");
        String[] newSplit = newContent.split("!\\[\\]");
        String[] result = split;
        //newSplit for i
        for (int i = 0; i < newSplit.length; i++) {
            // 이미지 URL이 아닌경우 Skip
            if(!isContains(newSplit[i])){
                newSplit[i] = "";
                continue;
            }
            //split[i] subString(indexof(http),indexof(")")
            newSplit[i]  =  customSplit(newSplit[i]);
        }
        //split for i
        for (int i = 0; i < split.length; i++) {
            // 이미지 URL이 아닌경우 Skip
            if(!isContains(split[i])){
                split[i] = "";
                continue;
            }
            //split[i] subString(indexof(http),indexof(")")
            split[i]  = customSplit(split[i]);
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

    private boolean isContains(String newContent) {
        return newContent.contains("![](https");
    }
}