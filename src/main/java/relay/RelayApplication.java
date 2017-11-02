package relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebFluxSecurity
@EnableReactiveMongoRepositories
public class RelayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelayApplication.class, args);
	}

}
