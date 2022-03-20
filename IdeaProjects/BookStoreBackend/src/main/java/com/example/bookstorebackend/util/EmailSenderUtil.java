package com.example.bookstorebackend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.core.io.FileSystemResource;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailSenderUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    // Method to send a simple mail containing otp
    public void sendMail(String toEmail,
                               String subject,
                               String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("rahul34jha@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(message);
        mailMessage.setSubject(subject);

        logger.info(subject);
        logger.info(toEmail);
        logger.info(message);

        mailSender.send(mailMessage);
        System.out.println("Mail Send");
    }

    // Method to send an email containing attachment
    public void sendEmailWithAttachment(String toEmail, String body,
                                        String subject, String attachment)
            throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("rahul34jha@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystem =
                new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);

        mailSender.send(mimeMessage);
        System.out.println("Mail send with attachment...");

    }
}
