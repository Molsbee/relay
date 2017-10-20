package relay.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import relay.model.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
