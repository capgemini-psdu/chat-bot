package service;

/**
 * Created by phil on 12/07/17.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.AcronymModel;


import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AcronymService {

    public static class Model {
        private int nextId = 1;
        private Map<Integer, AcronymModel> posts = new HashMap<>();


        public int createPost(String acronym, String text){
            //TODO: read and update id number
            int id = nextId++;
            AcronymModel acronymModel = new AcronymModel();
            acronymModel.setId(id);
            acronymModel.setAcronym(acronym);
            acronymModel.setText(text);
            posts.put(id, acronymModel);
            return id;
        }

        public List getAllPosts(){
            return  posts.keySet().stream().sorted().map((id) -> posts.get(id)).collect(Collectors.toList());
        }
    }

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }


}