package com.booleanuk.extension;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.HashMap;
import java.util.Random;

public class SMSOutbound {

    // Find your Account Sid and Token at console.twilio.com
    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    private static final String NHONNA_NUMBER = System.getenv("NHONNA_NUMBER");  // Could bots scan this and
                                                                                       // spam my private number?
    private static final String TWILIO_NUMBER = System.getenv("TWILIO_NUMBER");  // Does this number only work
                                                                                       // for me, or can they scan it too

    private final Inventory inventory;
    private final Random random;

    public SMSOutbound(Inventory inventory)
    {
        this.inventory = inventory;
        this.random = new Random();
        init();
    }

    public static void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendMessage(String msgBody)
    {
        Message message = Message
                .creator(
                        new PhoneNumber(NHONNA_NUMBER),
                        new PhoneNumber(TWILIO_NUMBER),
                        msgBody
                )
                .create();

        System.out.println(message.getSid());
    }

    public void sendOrderMessage(HashMap<String, Integer> productCounts)
    {
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("Your order summary:\n");
        for(String key: productCounts.keySet())
        {
            orderSummary.append(inventory.getName(key))
                    .append(" ").append(inventory.getVariant(key))
                    .append(" x").append(productCounts.get(key))
                    .append("\n");
        }
        orderSummary.append("Estimated delivery time: ").append(random.nextInt(10,60)).append("minutes");

        sendMessage(orderSummary.toString());
    }

}