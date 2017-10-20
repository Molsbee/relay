package relay.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import relay.model.Channel;

public interface ChannelRepository extends ReactiveMongoRepository<Channel, String> {
}
