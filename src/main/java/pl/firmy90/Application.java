package pl.firmy90;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
