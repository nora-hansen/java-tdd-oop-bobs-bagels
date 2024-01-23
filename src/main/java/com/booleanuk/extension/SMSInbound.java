package com.booleanuk.extension;

import static spark.Spark.post;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

public class SMSInbound {
    public static void main(String[] args)
    {
        Body b = new Body.Builder("boob").build();
        post("/receive-sms", (req, res) -> {
            Message sms = new Message.Builder()
                    .body(b)
                    .build();
            MessagingResponse twiml = new MessagingResponse.Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });
    }
}
