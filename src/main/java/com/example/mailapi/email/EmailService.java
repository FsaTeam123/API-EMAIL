package com.example.mailapi.email;

import com.example.mailapi.email.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from:no-reply@localhost}")
    private String defaultFrom;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(EmailRequest req) {
        try {
            MimeMessage mime = mailSender.createMimeMessage();
            boolean isHtml = Boolean.TRUE.equals(req.html());
            MimeMessageHelper helper = new MimeMessageHelper(mime, "UTF-8");
            helper.setFrom(defaultFrom);
            helper.setTo(req.to());
            helper.setSubject(req.subject());
            helper.setText(req.message(), isHtml);
            mailSender.send(mime);
        } catch (Exception e) {
            throw new MailSendException("Falha ao enviar e-mail: " + e.getMessage(), e);
        }
    }

    public static class MailSendException extends RuntimeException {
        public MailSendException(String msg, Throwable cause) { super(msg, cause); }
    }
}
