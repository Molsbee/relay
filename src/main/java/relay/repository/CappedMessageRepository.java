package relay.repository;

import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.CappedMessage;
import relay.model.Channel;

public interface CappedMessageRepository extends ReactiveCrudRepository<CappedMessage, String> {

    @Tailable
    Flux<CappedMessage> findByChannel(Mono<Channel> channel);

}
