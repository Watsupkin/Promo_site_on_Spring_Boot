package org.inoutad.inouttad.controllers;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.inoutad.inouttad.models.DataFromForm;
import org.inoutad.inouttad.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

@Controller
public class MainController {
    @Autowired
    MailSender mailSender;
    final static String NAME = "^[А-ЯЁ][а-яё]*$";
    final static String EMAIL = "^(.+)@(\\S+)$";
    final static String PHONE = "^((\\+7|7|8)+([0-9]){10})$";

    @GetMapping("/inoutad")
    public String main(Model model){
        return "inoutad";
    }

    @PostMapping("/inoutad")
    public String getForm(Model model,
                          @RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String phone,
                          @RequestParam String servSelect,
                          @RequestParam MultipartFile attachment) throws IOException, MessagingException {
        if (check(NAME, name,true) && check(EMAIL, email,true) && check(PHONE, phone,false)) {
            String message = "\nName: "+name+"\nE-mail: "+email+"\nPhone: "+phone+"\nService: "+servSelect+"\n";
            if (attachment != null) {
//                mailSender.sendWithAtt(message, attachment, email.split("@")[0]); in progress
            } else {
                mailSender.send(message);
            }
            return "result";
        }
        return "badResult";
    }

    static boolean check(String rule, String text, boolean isInsensitive){
        Pattern myRule;
        if (isInsensitive){
            myRule = Pattern.compile(rule, Pattern.CASE_INSENSITIVE);
        } else {
            myRule = Pattern.compile(rule);
        }
        var res = myRule.matcher(text);
        return res.matches();
    }
}
