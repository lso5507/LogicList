package swlee.logiclist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;



@ConfigurationProperties(prefix = "spring.")
class AWSConfig{
	public String environment;
	private String fileDir;
}
@SpringBootApplication
public class Application {
	@Value("${spring.environment}")
	private String environment;

	@Value("${spring.file-dir}")
	private String fileDir;
	/**
	 * @description 이미지, 영상 업로드할 폴더를 프로파일 별로 다른 경로에 생성한다.
	 */
	@PostConstruct
	private void init() {

		if (environment.equals("local")) {
			String staticFolder = System.getProperty("user.dir") + "/src/main/resources/static";
			mkdirResource(staticFolder);

			String files = System.getProperty("user.dir") + fileDir;
			mkdirResource(files);
		} else if (environment.equals("development")) {
			String filesFolder = "/var/www/html/files";
			mkdirResource(filesFolder);
		}
	}

	/**
	 * @param fileDir 생성을 위한 폴더명
	 * @description 주어진 경로에 폴더를 생성함
	 */
	private static void mkdirResource(String fileDir) {
		File Folder = new File(fileDir);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); //폴더 생성합니다.
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}



	// AWS사용을 위해 설정파일 인식 로직
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:aws.yml,"
			+ "classpath:application.properties";

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
//		SpringApplication.run(Application.class, args);
	}

}
