package opgg.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    private final String FROM_ADDRESS = "michael0622@naver.com";

    public void sendVerificationEmail(String toEmail, String token) {
        String subject = "이메일 인증 요청";
        String verificationUrl = "http://localhost:8080/user/verify?token=" + token;

        String content = "<p>아래 링크를 클릭하여 이메일 인증을 완료하세요.</p>"
                + "<a href=\"" + verificationUrl + "\">이메일 인증하기</a>";

        try {
            log.info("이메일 전송 준비: {}", toEmail);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(FROM_ADDRESS);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("이메일 전송 성공: {}", verificationUrl);
        } catch (MessagingException e) {
            log.error("이메일 전송 실패: {}", toEmail, e);
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }
}