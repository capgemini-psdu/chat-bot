package com.chatbot.controller;

import com.chatbot.model.AcronymModel;
import com.chatbot.service.AcronymService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by davidogbonnah on 29/08/2017.
 */
public class CreateController {


    AcronymService.Model model = new AcronymService.Model();

    @RequestMapping(value = "/create", produces={"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<String> createAcronym(@RequestBody AcronymModel acronymModel) {
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
