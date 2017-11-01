package relay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Channel;
import relay.model.User;
import relay.model.web.CreateChannelRequest;
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
    public Mono<Channel> getChannel(@PathVariable String channelName) {
        return channelService.findByName(channelName);
    }

    @PostMapping
    public void create(@RequestBody CreateChannelRequest request) {
        channelService.create(request.getName(), new User());
    }

    @DeleteMapping
    public void delete(String name) {
        channelService.delete(name, new User());
    }

}
