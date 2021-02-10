package ro.inf.ucv.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("ro.inf.ucv"))
				.paths(PathSelectors.ant("/api/**")).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Search Service", "Search Service API", "1.0", "https://www.apache.org/licenses/", contact(),
				"Apache License 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

	private Contact contact() {
		return new Contact("UCV", "http://inf.ucv.ro", "andreea@inf.ucv.ro");
	}
}
