package br.com.grupodass.ted.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import br.com.grupodass.ted.domain.config.annotation.Dev;

@Dev
public class TestConfig {
	
	@Value("${server.servlet.context-path}")
	private String applicationPath;

	@Value("${server.port}")
	private String applicationPort;

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Bean
	public CommandLineRunner init() {
		return args ->{
			System.out.println("Inicializando aplicação <"+applicationName+">\nCaminho Raiz: localhost:"+applicationPort+applicationPath);
		};
	}
	
	
	
}
