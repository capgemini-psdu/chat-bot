/**
 * Created by phil on 12/07/17.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.chatbot")
@EnableAutoConfiguration

public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }



}