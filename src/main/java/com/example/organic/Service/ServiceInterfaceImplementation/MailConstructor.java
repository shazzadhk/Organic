package com.example.organic.Service.ServiceInterfaceImplementation;

import com.example.organic.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailConstructor {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String contextPath, String token, Users users) throws MessagingException {

        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your Email:<br>"
                + "<h3><a href=\"[[URL]]\">Confirm Your Email</a></h3>"
                + "Thank you,<br>"
                + "Organic Team.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(users.getEmail());
        helper.setSubject("Verify Your Email");
        helper.setFrom("shazzadhk12@gmail.com");

        content = content.replace("[[name]]", users.getFirstName());
        String verifyURL = contextPath + "/newUser?token=" +token;

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);


    }

    public void sendForgetPasswordEmail(String contextPath, String token, Users users) throws MessagingException {

        String content = "Dear [[name]],<br>"
                + "Please click the link below to reset your Password:<br>"
                + "<h3><a href=\"[[URL]]\">Reset Your Password</a></h3>"
                + "Thank you,<br>"
                + "Organic Team.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(users.getEmail());
        helper.setSubject("Reset Your Password");
        helper.setFrom("shazzadhk12@gmail.com");

        content = content.replace("[[name]]", users.getFirstName());
        String verifyURL = contextPath + "/resetPassword?token=" +token;

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);


    }
}
