package relay.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import relay.model.Message;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
}
