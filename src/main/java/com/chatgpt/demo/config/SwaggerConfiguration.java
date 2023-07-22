package com.chatgpt.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Generated
public class SwaggerConfiguration {
    @Value("${swagger.openApi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI apiInfo() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("suryakalyan.test01@gmail.com");
        contact.setName("Surya Kalyan");
        contact.setUrl("https://openai.com/");

        License license = new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("OpenAI ChatGPT Using SpringBoot Application")
                .version("1.0")
                .contact(contact)
                .description("Documentation for OpenAI ChatGPT Api.")
                .license(license);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }

    @Bean
    public GroupedOpenApi httpApi() {
        return GroupedOpenApi.builder()
                .displayName("OpenAI ChatGPT Using SpringBoot Application")
                .group("http")
                .pathsToMatch("/**")
                .build();
    }
}
