/*
 * (c) 2021 IBM Financial Industry Solutions GmbH, All rights reserved.
 */

package com.ibm.nettrader.brokerage.swagger.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
  /**
   * Api.
   *
   * @return the docket
   */
  @Bean
    public Docket api() { 
    return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.ibm.nettrader.brokerage"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(metaInfo());
  }
  
  /**
   * Meta info.
   *
   * @return the api info
   */
  private ApiInfo metaInfo() {
    return new ApiInfo(
                "SwaggerAPI Documentation",
                "All Resource EndPoints",
                "1.0",
                " ",
                new Contact("", " ", "michael.moslerkrings@de.ibm.com"),
                "License Version 2.0",
                "https://www.apache.org/licesen.html", 
                Collections.emptyList());
  }

}
