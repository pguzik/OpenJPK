package pl.pguzik.jpk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class OpenJpkApplication {

	public static String UPLOADED = "upload-dir";
	public static String CONVERTED_TO_UTF8 = "converted-to-utf8";

	public static void main(String[] args) {
		SpringApplication.run(OpenJpkApplication.class, args);
	}

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(UPLOADED).mkdir();
            new File(CONVERTED_TO_UTF8).mkdir();
        };
    }
}
