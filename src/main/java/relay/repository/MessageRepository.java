package relay.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Channel;
import relay.model.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, String> {

    Flux<Message> findByChannel(Mono<Channel> channel);

}
