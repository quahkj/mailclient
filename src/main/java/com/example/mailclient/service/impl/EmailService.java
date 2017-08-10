package com.example.mailclient.service.impl;

import com.example.mailclient.domain.EmailParam;

/**
 * Created by quahk on 9/08/2017.
 */
public interface EmailService {

    int sendEmail(EmailParam emailParam) throws Exception;
}
