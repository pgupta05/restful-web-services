package com.pradip.rest.webservices.restfulwebservices;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Configuration
@Configuration
//Enable Swagger
@EnableSwagger2
public class SwaggerConfig {

	 public static final Contact DEFAULT_CONTACT = new Contact("Pradip Gupta", "407-5329092", "");
	  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Pradip Documentation", "Test Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
     private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
    		 new HashSet<String>();
	//Bean - Docket
	   // Swagger 2
	   // All the paths
	   // All the apis
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
		
	}

}
