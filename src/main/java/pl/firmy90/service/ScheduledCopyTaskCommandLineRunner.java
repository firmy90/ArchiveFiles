package pl.firmy90.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.firmy90.AppProperties;
import pl.firmy90.service.interfaces.CopyTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
@Slf4j
@AllArgsConstructor
public class ScheduledCopyTaskCommandLineRunner implements CommandLineRunner {
    @Autowired
    private CopyTask copyTask;
    private AppProperties appProperties;

    @Override
    public void run(String... args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(copyTask::run, 0,
                appProperties.getInterval().getValue(),
                appProperties.getInterval().getUnit());

    }
}
