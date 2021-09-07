package com.texo.pioresfilmes;

import com.texo.pioresfilmes.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class PioresFilmesApplication implements CommandLineRunner {

	@Autowired
	CSVService csvService;

	public static void main(String[] args) {
		SpringApplication.run(PioresFilmesApplication.class, args);
	}

	@Bean
	public Docket swagger(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Override
	public void run(String... args) throws Exception {
		csvService.importCsv();
	}
}
