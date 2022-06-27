// package br.com.carros.controllers;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;

// @Configuration
// public class SpringFoxConfig {

//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .select()
//                 .apis(RequestHandlerSelectors.basePackage("br.com.carros.controllers"))
//                 .paths(PathSelectors.any())
//                 .build();
//     }

//     // private ApiInfo apiInfo() {
//     // return new ApiInfoBuilder()
//     // .contact(new Contact("Lucas Fernandes", "lucasfernandesdev.com.br",
//     // "lucas@lucasfernandesdev.com.br"))
//     // .title("Carros")
//     // .description("Documentação API dos Carros")
//     // .license("Apache Licence Version 2.0")
//     // .licenseUrl("https://apache.org")
//     // .version("1.0")
//     // .build();

//     // }
// }
