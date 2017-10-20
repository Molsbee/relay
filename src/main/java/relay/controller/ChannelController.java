package relay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Channel;
import relay.model.User;
import relay.service.ChannelService;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping
    public Flux<Channel> getChannels() {
        return channelService.findAll();
    }

    @GetMapping("/{channelName}")
    public Mono<Channel> getChannel(String channelName) {
        return channelService.findByName(channelName);
    }

    @PostMapping
    public void create(String name) {
        channelService.create(name, new User());
    }

}
