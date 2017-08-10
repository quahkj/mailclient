package com.example.mailclient.service.impl;

import com.example.mailclient.domain.EmailParam;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quahk on 9/08/2017.
 */
@Service
public class EmailServiceImpl implements EmailService {

    public int sendEmail(EmailParam emailParam) throws Exception {

        int responseCode;
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("api","key-71f81edacc5a6813a0ee80df2447dc72");
        credsProvider.setCredentials(AuthScope.ANY, credentials);

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        try {
            HttpPost httpPost = new HttpPost("https://api.mailgun.net/v3/sandboxbd0077450e794397a7b54c0fe0233131.mailgun.org/messages");
            List <NameValuePair> param = new ArrayList <NameValuePair>();
            param.add(new BasicNameValuePair("from", "Mailgun Sandbox <postmaster@sandboxbd0077450e794397a7b54c0fe0233131.mailgun.org>"));
            param.add(new BasicNameValuePair("to", emailParam.getTo()));
            if(emailParam.getCc() != null) {
                param.add(new BasicNameValuePair("cc", emailParam.getCc()));
            }
            if(emailParam.getBcc() != null) {
                param.add(new BasicNameValuePair("bcc", emailParam.getBcc()));
            }
            param.add(new BasicNameValuePair("subject", emailParam.getSubject()));
            param.add(new BasicNameValuePair("text", emailParam.getText()));
            httpPost.setEntity(new UrlEncodedFormEntity(param));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println("====>" +response2.getStatusLine());
                responseCode = response2.getStatusLine().getStatusCode();
                HttpEntity entity2 = response2.getEntity();
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }

        return responseCode;
    }
}
