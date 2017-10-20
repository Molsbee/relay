package relay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Channel;
import relay.model.User;
import relay.repository.ChannelRepository;

import java.time.LocalDateTime;

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

    public void create(String name, User createdBy) {
        channelRepository.save(new Channel(name, createdBy, LocalDateTime.now()));
    }

}
