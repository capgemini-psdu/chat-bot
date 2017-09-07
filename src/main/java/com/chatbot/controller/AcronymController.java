package com.chatbot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.chatbot.service.AcronymService;

import static com.chatbot.service.AcronymService.dataToJson;

/**
 * Created by davidogbonnah on 25/08/2017.
 */

@RestController
public class AcronymController {

    AcronymService.Model model = new AcronymService.Model();


    @RequestMapping(value = "/acronym", produces={"application/json"})
    public String acronym(@RequestParam(value="question", defaultValue="OMG") String question) {

        System.out.print("User asked "+question);

        return dataToJson(model.getAcronyms(question));

    }
}
