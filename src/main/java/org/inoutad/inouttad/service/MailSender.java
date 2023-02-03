package org.inoutad.inouttad.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;

    public void send(String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(username);
        mailMessage.setSubject("Заявка с сайта");
        mailMessage.setText(message);


        mailSender.send(mailMessage);
    }
// in progress
//    public void sendWithAtt(String message, MultipartFile file, String userMail) throws MessagingException {
//        MimeMessage mailMessage = mailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);
//
//        messageHelper.setFrom(username);
//        messageHelper.setTo(username);
//        messageHelper.setSubject("Заявка с сайта");
//        messageHelper.setText(message);
////        messageHelper.addInline("", FileUtils.getImage());
//        messageHelper.addAttachment("Тз от ".concat(userMail), file);
//
//
//        mailSender.send(mailMessage);
//    }
}
