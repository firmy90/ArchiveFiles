package pl.firmy90;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmailSenderLogicIT {
    @RegisterExtension
    GreenMailExtension greenMail = new GreenMailExtension();
    
    @Autowired
    private EmailProperties emailProperties;

    @Test
    public void shouldSendEmail() throws MessagingException {
        //given
        String from = emailProperties.getFrom();
        String to = emailProperties.getTo();
        String subject = emailProperties.getSubject();
        String content = emailProperties.getContent();

        //when
        GreenMailUtil.sendTextEmailTest(from, to, subject, content);

        //then
        MimeMessage[] emails = greenMail.getReceivedMessages();
        MimeMessage email = emails[0];
        assertEquals(1, emails.length);
        assertEquals("Udana kompresja pliku", email.getSubject());
        assertEquals("Pliki skompresowane do folderu", GreenMailUtil.getBody(email));
    }

}
