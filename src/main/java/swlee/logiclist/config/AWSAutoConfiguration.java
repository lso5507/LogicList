package swlee.logiclist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionalOnClass(S3UploaderController.class)
@EnableConfigurationProperties(AWSConfig.class)
public class AWSAutoConfiguration {
    @Autowired
    private AWSConfig awsConfig;

}
