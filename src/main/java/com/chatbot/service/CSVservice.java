package com.chatbot.service;

import com.chatbot.model.AcronymModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by phil on 13/07/17.
 */
public class CSVservice {

    private static final String COMMA = ",";
    private static final String HEADER="Ind,Acronym,Positive votes,Negative votes,Description\n";

    private Function<String, AcronymModel> mapToItem = (line) -> {
        String[] p = line.split(COMMA);
        AcronymModel item = new AcronymModel();
        item.setId(Integer.parseInt(p[0]));
        item.setAcronym(p[1]);
        item.setPositiveVote(p[2]);
        item.setNegativeVote(p[3]);
        item.setText(p[4]);
        return item;
    };

    public CSVservice() {
    }

    public List<AcronymModel> processInputFile(String inputFilePath) {

        List<AcronymModel> inputList = new ArrayList<AcronymModel>();

        try {

            File inputF = new File(inputFilePath);

            InputStream inputFS = new FileInputStream(inputF);

            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // ignor the csv header
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

            br.close();

        } catch (IOException e) {
            System.out.print("Cant find acronym csv file: \n\n" + e.getMessage());
            System.exit(-1);
        }

        return inputList;

    }

    public void processOutputFile(String outputFilePath, List<AcronymModel> list){

        try {
            FileWriter  writer = new FileWriter(outputFilePath);
            StringBuilder sb = new StringBuilder();

            writer.append(HEADER);

            list.forEach(item->{
                try {
                    writer.append(item.getId()+","+item.getAcronym()+","+item.getPositiveVote()+","+item.getNegativeVote()+","+item.getText()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.print("Can't save acronym properties to csv file: \n\n" + e.getMessage());
            System.exit(-1);
        }


    }


}