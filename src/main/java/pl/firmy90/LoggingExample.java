package pl.firmy90;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class LoggingExample {

    @PostConstruct
    public void exampleLogging() {
        log.error("this is error");
        log.warn("this is a warning");
        log.info("this is info");
        log.debug("this is debug");
        log.trace("this is trace");
    }
}