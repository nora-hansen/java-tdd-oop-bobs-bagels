package com.booleanuk.extension;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {

    // Find your Account Sid and Token at console.twilio.com
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String NHONNA_NUMBER = System.getenv("NHONNA_NUMBER");  // Could bots scan this and
                                                                                      // spam my private number?
    public static final String TWILIO_NUMBER = System.getenv("TWILIO_NUMBER");  // Does this number only work
                                                                                      // for me, or can they scan it too

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(
                        new PhoneNumber(NHONNA_NUMBER),
                        new PhoneNumber(TWILIO_NUMBER),
                        "Coolest SMS in the world! WOW!!"
                )
                .create();

        System.out.println(message.getSid());
    }
}