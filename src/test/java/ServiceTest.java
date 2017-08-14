/**
 * Created by phil on 12/07/17.
 */


import model.AcronymModel;
import org.junit.Test;

import service.AcronymService;

import java.util.List;

import static org.junit.Assert.*;


public class ServiceTest {

    String testValue1="Minimum viable product";
    AcronymService.Model model = new AcronymService.Model();

    @Test
    public void test_createPost() throws Exception {
        int id =model.createPost("MVP",testValue1 );
        List<AcronymModel> acronymModelList =  model.getAllPosts();
        assertTrue(acronymModelList.get(id).getText().equals(testValue1));
    }

//TODO: tests for get acronym and test for voting

}
