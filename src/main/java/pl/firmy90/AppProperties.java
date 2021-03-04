package pl.firmy90;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "application.backup")
@Getter
@Setter
public class AppProperties {
    private String name;
    private String source;
    private String destination;
    private Interval interval;

    @Getter
    @Setter
    public static class Interval {
    private Integer value;
    private TimeUnit unit;

    }


}


