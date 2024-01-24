package com.booleanuk.extension;

import static spark.Spark.post;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SMSInbound {
    public static void main(String[] args)
    {
        Inventory inv = new Inventory();

        post("/receive-sms", (req, res) -> {
            Body b = new Body.Builder("What's did you say? I don't have ears... Was it " + req.queryParams("Body") + "?").build();

            Message sms = new Message.Builder()
                    .body(b)
                    .build();
            MessagingResponse twiml = new MessagingResponse.Builder()
                    .message(sms)
                    .build();
            placeSMSOrder(req.queryParams("Body"), inv);
            return twiml.toXml();
        });
    }



    public static boolean placeSMSOrder(String requestBody, Inventory inv)
    {
        Pattern p = Pattern.compile("[0-9]+");
        HashMap<String, Integer> order = new HashMap<>();
        String[] words = requestBody.split("[\\s\\t\\n]");

        int amount = -1;
        for (String word : words) {
            System.out.println("Current word " + word);
            if (amount < 0) {
                Matcher m = p.matcher(word);
                if (m.matches()) {
                    System.out.println("A NUMBER: " + word);
                    amount = Integer.parseInt(word);
                }
            } else if (inv.getVariants().containsValue(word)) {
                System.out.println("Let's put");
                order.put(word, amount);
                amount = -1;
            }
        }

        System.out.println("Order" + order);

        return true;
    }
}
