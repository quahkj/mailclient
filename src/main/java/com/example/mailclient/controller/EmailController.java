package com.example.mailclient.controller;

import com.example.mailclient.domain.EmailParam;
import com.example.mailclient.domain.EmailResult;
import com.example.mailclient.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quahk on 9/08/2017.
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailParam emailParam) throws Exception {

        System.out.println("emailParam = " + emailParam);
        int responseCode = this.emailService.sendEmail(emailParam);
        EmailResult emailResult = new EmailResult();
        emailResult.setStatus(responseCode);
        return new ResponseEntity<EmailResult>(emailResult, HttpStatus.OK);

    }
}
