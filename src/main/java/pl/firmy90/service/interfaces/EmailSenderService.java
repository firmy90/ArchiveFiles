package pl.firmy90.service.interfaces;

public interface EmailSenderService {
    void sendEmail(String from, String to,  String subject, String content);
}