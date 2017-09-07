package com.chatbot.service;

/**
 * Created by phil on 12/07/17.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.chatbot.model.AcronymModel;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;


public class AcronymService {

    private static final String CSV_PATH="src/main/resources/acronyms.csv";

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public static class Model {
        CSVservice csvService = new CSVservice();
        List<AcronymModel> acronymList = csvService.processInputFile(CSV_PATH);
        private int nextId = 1;

        public int createPost(String acronym, String text) {
            //TODO: update to and reload CSV
            int id = acronymList.size();
            AcronymModel acronymModel = new AcronymModel();
            acronymModel.setId(id);
            acronymModel.setAcronym(acronym);
            acronymModel.setText(text);
            acronymModel.setPositiveVote("0");
            acronymModel.setNegativeVote("0");
            acronymList.add(acronymModel);
            acronymList.forEach(item->System.out.println(item.getAcronym()));
            return id;
        }

        public boolean addPostVote(AcronymModel acronymModel) {

            int vote= acronymModel.getVote();
            int ind=acronymModel.getId()-1;

            acronymModel=acronymList.get(ind);// we need this to safeguard user changing text

            //TODO: also set CSV file
            if(vote==1){
                int currentPossitiveVote=Integer.parseInt(acronymModel.getPositiveVote());
                acronymModel.setPositiveVote(""+(currentPossitiveVote++));
                acronymList.set(ind,acronymModel);
                csvService.processOutputFile(CSV_PATH,acronymList);
                return true;
            }else if(vote==-1){
                int currentNegativeVote=Integer.parseInt(acronymModel.getNegativeVote());
                acronymModel.setNegativeVote(""+(currentNegativeVote+1));
                acronymList.set(ind,acronymModel);
                csvService.processOutputFile(CSV_PATH,acronymList);
                return true;
            }

            return false;
        }

        public List getAllPosts() {
            return acronymList;
        }
        public List getAcronyms(String userText) {
          return  acronymList.stream().filter(x -> x.getAcronym().equals(getAcronymFromQuestion(userText))).collect(Collectors.toList());
        }

        private String getAcronymFromQuestion(String userText){
            String outputString = "";
            String words[]= userText.split(" ");

            for (int i=0;i<words.length;i++){
                String word=words[i];

                for (int j = 0; j < word.length();j++) {
                    char c = word.charAt(j);
                    outputString += Character.isUpperCase(c) ? c : "";
                }
                if(outputString.length()>2){
                    break;
                }
                outputString = "";
            }

            return outputString;
        }

    }


}