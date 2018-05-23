package relay.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Channel;
import relay.model.Message;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    Flux<Message> findByChannel(Mono<Channel> channel);

}
