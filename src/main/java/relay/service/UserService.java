package relay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.User;
import relay.repository.UserRepository;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelService channelService;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findByName(String name) {
        return userRepository.findById(name);
    }

    public void create(User user) {
        findByName(user.getUsername())
                .defaultIfEmpty(user)
                .map(u -> userRepository.save(u)
                        .doOnError(e -> log.error("error occurred saving user {}", e))
                )
                .subscribe();
    }

    public void subscribeToChannel(User user, String channelName) {
        channelService.findByName(channelName)
                .subscribe(
                        c -> {
                            user.addChannel(c);
                            userRepository.save(user).subscribe();
                        }
                );
    }

}
