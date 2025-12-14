package br.com.eicon.consultacredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("br.com.eicon.consultacredito")
public class ConsultacreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultacreditoApplication.class, args);
	}

}
