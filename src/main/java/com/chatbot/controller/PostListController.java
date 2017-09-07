package com.chatbot.controller;

import com.chatbot.service.AcronymService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.chatbot.service.AcronymService.dataToJson;
/**
 * Created by davidogbonnah on 25/08/2017.
 */

@RestController
public class PostListController {

    AcronymService.Model model = new AcronymService.Model();

    @RequestMapping(value = "/all", produces={"application/json"})
    public String postList(String list) {

        return dataToJson(model.getAllPosts());
    }
}
