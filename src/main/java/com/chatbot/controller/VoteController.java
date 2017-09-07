package com.chatbot.controller;

import com.chatbot.service.AcronymService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.chatbot.model.AcronymModel;

/**
 * Created by davidogbonnah on 25/08/2017.
 */

@RestController
public class VoteController {

    AcronymService.Model model = new AcronymService.Model();

    @RequestMapping(value = "/vote", produces={"application/json"}, method = RequestMethod.POST)
    public ResponseEntity <String> persistVote(@RequestBody AcronymModel acronymModel) {
        try {

            if (!acronymModel.isValid()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            model.addPostVote(acronymModel);
            return ResponseEntity.status(HttpStatus.OK).body(null);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
