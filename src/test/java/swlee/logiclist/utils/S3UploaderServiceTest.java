package swlee.logiclist.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class S3UploaderServiceTest {
    @Autowired
    S3UploaderService s3UploaderService;

    @Test
    public  void S3remove(){

        s3UploaderService.removeS3File("image/107e43ca-180a-4dcd-a6ac-b69ea4a241ce39ebe2b8-ff53-4d7e-9272-646f8cc34bf3.jpeg","logiclist");

    }

}