package relay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Channel;
import relay.repository.ChannelRepository;

@Slf4j
@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public Flux<Channel> findAll() {
        return channelRepository.findAll();
    }

    public Mono<Channel> findByName(String name) {
        return channelRepository.findById(name);
    }

    public void create(String name) {
        findByName(name)
                .defaultIfEmpty(new Channel(name))
                .map(c -> {
                    log.info("attempting to save channel {}", c.getName());
                    return channelRepository.save(c)
                            .subscribe(
                                    cn -> log.info("saved channel {}", cn.getName()),
                                    e -> log.error("error occurred saving channel name {}", e)
                            );
                }).subscribe();
    }

    public void delete(String name) {
        findByName(name)
                .subscribe(c -> {
                    channelRepository.delete(c).subscribe();
                });
    }

}
