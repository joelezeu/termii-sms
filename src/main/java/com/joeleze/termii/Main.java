package com.joeleze.termii;

import com.joeleze.termii.domain.MediaData;
import com.joeleze.termii.domain.NotificationRequest;
import com.joeleze.termii.domain.NotificationResponse;
import com.joeleze.termii.services.NotificationService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static String API_ENDPOINT = "https://v3.api.termii.com/api/sms/send";
    private static String API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    public static void main(String[] args) {



        MediaData mediaData = new MediaData.Builder()
                .url("https://media.example.com/file")
                .caption("Your caption").build();

        NotificationRequest smsRequest = new NotificationRequest.Builder()
                .to("2348138249630")
                .from("N-Alert")
                .sms("Hi there, testing Termii")
                .channel("dnd")//whatsapp, generic, dnd seem to be the only channels that works currently
                .apiKey(API_KEY)
                //.media(mediaData)
                .build();

        NotificationService smsService = new NotificationService(API_ENDPOINT);
        try {
            NotificationResponse response = smsService.sendNotification(smsRequest);
            System.out.println("SMS sent successfully! "+response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}