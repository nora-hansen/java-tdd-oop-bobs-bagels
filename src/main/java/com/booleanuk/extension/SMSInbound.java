package com.booleanuk.extension;

import static spark.Spark.post;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SMSInbound {
    public static void main(String[] args)
    {
        // Pattern p = Pattern.compile(".*&Body=(.*)&FromCountry");

        post("/receive-sms", (req, res) -> {
            //Matcher m = p.matcher(req.body());
            //if(m.find())
            //{
            //    placeSMSOrder(m.group(1));
            //}

            Body b = new Body.Builder("What's did you say? I don't have ears... Was it " + req.queryParams("Body") + "?").build();

            Message sms = new Message.Builder()
                    .body(b)
                    .build();
            MessagingResponse twiml = new MessagingResponse.Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });
    }

    public static boolean placeSMSOrder(String requestBody)
    {
        String[] values = requestBody.split("\\+");
        System.out.println("Message contains " + Arrays.toString(values));
        return true;
    }
}
