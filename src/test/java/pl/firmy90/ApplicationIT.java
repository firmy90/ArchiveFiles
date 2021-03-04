package pl.firmy90;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@ActiveProfiles("test")
@Slf4j
class ApplicationIT {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private EmailProperties emailProperties;



    @Test
    void contextLoads() {
    }

    @Test
    public void shouldReturnApplicationName() {
        String applicationName = appProperties.getName();
        assertThat(applicationName).isEqualTo("PuzzlesInSpringTest");
    }

    @Test
    public void shouldReturnBackupSource() {
        String applicationName = appProperties.getSource();
        assertThat(applicationName).isEqualTo("from-dir");
    }

    @Test
    public void shouldReturnBackupDestination() {
        String applicationName = appProperties.getDestination();
        assertThat(applicationName).isEqualTo("to-dir");
    }

    @Test
    public void shouldReturnEmailSender() {
        String applicationName = emailProperties.getFrom();
        assertThat(applicationName).isEqualTo("nawakbeata@gmail.com");
    }

    @Test
    public void shouldReturnIntervalValue(){
        Integer value = appProperties.getInterval().getValue();
        assertThat(value).isEqualTo(3);
    }

    @Test
    public void shouldReturnIntervalUnit(){
        TimeUnit unit = appProperties.getInterval().getUnit();
        assertThat(unit).isEqualTo(TimeUnit.HOURS);
    }



}
