package com.carara.orderdelivery;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

public class OrderDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderDeliveryApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        Contact contact = new Contact();
        contact.setName("Pedro Carara");
        contact.setUrl("https:github.com/cararax");
        contact.setEmail("cararax@gmail.com");
        Server server = new Server();
        server.setUrl("https://order-delivery-service.herokuapp.com/");
        server.setDescription("Deployment on Heroku");
        ArrayList<Server> servers = new ArrayList<>();
        servers.add(server);

        return new OpenAPI().info(new Info()
                        .title("Order Delivery API")
                        .version(appVersion)
                        .description("This project is an order delivery api. The service allows creating orders, " +
                                "listing products and orders with products as well as delivering an order.")
                        .contact(contact)
                        .license(new License().name("GPT 2")
                                .url("https://github.com/openai/gpt-2/blob/master/LICENSE")))
                .servers(servers);
    }

}
