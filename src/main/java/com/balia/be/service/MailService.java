package com.balia.be.service;

import com.balia.be.domain.MUser;
import com.balia.be.service.util.SystemUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${application.mail.from}")
    private String mailFrom;

    @Value("${application.mail.base-url}")
    private String mailBaseUrl;
    
    @Autowired
    SystemUtil systemUtil;

    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void sendCreationEmail(MUser user) throws MessagingException, UnsupportedEncodingException {
        logger.info("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "creationEmail", "email.activation.title");
    }

    @Async
    public void sendEmail(String to, String subject, String content, String fromAddress, String senderName,
                          boolean isMultipart, boolean isHtml) {
        logger.info("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart);
//            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(fromAddress, senderName);
            message.setSubject(subject);
            message.setText(content, isHtml);
            mailSender.send(mimeMessage);
            logger.info("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.warn("Email could not be sent to user '{}'", to, e);
            } else {
                logger.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendEmailFromTemplate(MUser user, String templateName, String titleKey) {
        Locale locale = Locale.forLanguageTag("en");
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseUrl", systemUtil.getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(user.getEmail(), subject, content, mailFrom, mailBaseUrl, false, true);

    }
    
}
