package crudweb2.crudweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "CRUD WEB2",
        version = "1.0",
        description = "API para utilizar no trabalho da disciplina de WEB2",
        contact = @Contact(
            name = "William Guilherme Dahlke",
            email = "williamgdahlke@gmail.com"
        )        
    ),
    servers = @Server(url = "http://localhost:8080")    
)

@SpringBootApplication
public class Crudweb2Application {

	public static void main(String[] args) {
		SpringApplication.run(Crudweb2Application.class, args);
	}

}
