package pl.firmy90.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.firmy90.service.interfaces.EmailSenderService;

@Service
@AllArgsConstructor
public class EmailSenderImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;



    @Override
    public void sendEmail(String from, String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);

    }


}
