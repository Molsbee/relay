package relay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class RelayApplication {

	@Autowired
	private MongoOperations template;

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		return objectMapper;
	}

	@PostConstruct
	public void setupCappedCollections() {
		if (!template.collectionExists("capped_message")) {
			template.createCollection("capped_message", CollectionOptions.empty().capped().size(1000).maxDocuments(2000));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RelayApplication.class, args);
	}

}
