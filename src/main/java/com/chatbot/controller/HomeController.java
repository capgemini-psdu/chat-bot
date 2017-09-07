package com.chatbot.controller;

import java.text.SimpleDateFormat;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

/**
 * Created by davidogbonnah on 25/08/2017.
 */

@RestController
public class HomeController {

    String amPm = new SimpleDateFormat("aa").format(System.currentTimeMillis());
    String message = amPm.equals("PM") ? "afternoon" : "morning";
    String output = "Good " + message + ", how can I help?";

    @RequestMapping("/")
    public String home(String message) {

        return output;
    }


}
