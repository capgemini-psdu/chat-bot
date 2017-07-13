/**
 * Created by phil on 12/07/17.
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.AcronymModel;
import service.AcronymService;

import java.text.SimpleDateFormat;

import static service.AcronymService.dataToJson;
import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    private static final int HTTP_BAD_REQUEST = 400;

    public static void main(String[] args) {


        AcronymService.Model model = new AcronymService.Model();

        get("/", (req, res) -> {
            String amPm = new SimpleDateFormat("aa").format(System.currentTimeMillis());
            String message = amPm.equals("PM") ? "afternoon" : "morning";
            return "Good " + message + ", how can I help?";
        });

        get("/all", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return dataToJson(model.getAllPosts());
        });
        get("/acronym", (request, response) -> {
            String question = request.queryParams("question");
            System.out.print("User asked "+question);
            response.status(200);
            response.type("application/json");
            return dataToJson(model.getAcronyms(question));
        });

        post("/vote", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                AcronymModel acronymModel = mapper.readValue(request.body(), AcronymModel.class);

                if (!acronymModel.isValid()) {
                    response.status(HTTP_BAD_REQUEST);
                    return "";
                }

                boolean suc= model.addPostVote(acronymModel);
                response.status(200);
                response.type("application/json");
                return suc;

            } catch (JsonParseException jpe) {
                response.status(HTTP_BAD_REQUEST);
                return "";
            }
        });


        post("/create", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                AcronymModel acronymModel = mapper.readValue(request.body(), AcronymModel.class);

                if (!acronymModel.isValid()) {
                    response.status(HTTP_BAD_REQUEST);
                    return "";
                }

                int id = model.createPost(acronymModel.getAcronym(), acronymModel.getText());
                response.status(200);
                response.type("application/json");
                return id;
            } catch (JsonParseException jpe) {
                response.status(HTTP_BAD_REQUEST);
                return "";
            }
        });


    }


    interface Validable {
        boolean isValid();
    }
}