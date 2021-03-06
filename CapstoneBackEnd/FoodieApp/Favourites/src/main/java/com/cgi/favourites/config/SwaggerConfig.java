package com.cgi.favourites.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * As in this class we are implementing Swagger So annotate the class with @Configuration and
 * @EnableSwagger2
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*
     * Annotate this method with @Bean . This method will return an Object of Docket.
     * This method will implement logic for swagger
     */

    @Bean
    public Docket productApi() {

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.cgi.favourites"))
                .build()
                .apiInfo(setDetails())
                .useDefaultResponseMessages(false);


    }

    public ApiInfo setDetails()
    {
        ApiInfoBuilder apibuild=new ApiInfoBuilder();
        apibuild.title("Favourites App").version("Ver 1.1").description("This app is used to provide Favourites Endpoints")
                .license("Dimple@cgi.com");

        return apibuild.build();
    }


}
