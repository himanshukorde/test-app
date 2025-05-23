package com.CarHub.service;

import com.CarHub.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioSmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public String sendSms(String to, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),
                messageBody
        ).create();

        return message.getSid(); // Returns message SID
    }
}
