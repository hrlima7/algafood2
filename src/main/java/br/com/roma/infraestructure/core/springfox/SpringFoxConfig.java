package br.com.roma.infraestructure.core.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.models.Tag;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer{
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.roma.api"))
			  //.paths(PathSelectors.ant("/restaurantes/*"))
				.build()
			//.globalResponseMessage(RequestMethod.GET, ResponseMessageBuilder)
			.apiInfo(apiInfo()) //aplicacao das informacoes do Metodo apiInfo
			.tags(new springfox.documentation.service.Tag("cidades", "Descricao da cidade")); //inseri descricao
		
	}
	
	public ApiInfo apiInfo() { //Metodo para configurar o cabecalho e informacoes adcionais da documentacao
		return new ApiInfoBuilder()
				.title("CookFoodApi")
				.description("Api Aberta para cliente e restaurantes")
				.version("2")
				.build();
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	

}
