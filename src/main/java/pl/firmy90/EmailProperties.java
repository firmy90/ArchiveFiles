package pl.firmy90;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Getter
@Setter
@Configuration
public class EmailProperties {
    private final Resource resource;
    private Properties properties;
    private String from;
    private String to;
    private String subject;
    private String content;
    private String username;
    private String password;

    public EmailProperties() {
        this.resource = new ClassPathResource("email.properties");
        this.properties = new Properties();
    }

    @PostConstruct
    public void init() {
        try {
            this.properties.load(new InputStreamReader(this.resource.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.from = properties.getProperty("email.from");
        this.to = properties.getProperty("email.to");
        this.subject = properties.getProperty("email.subject");
        this.content = properties.getProperty("email.content");

    }
}


