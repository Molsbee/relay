package relay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Message;
import relay.model.User;
import relay.model.web.CreateMessageRequest;
import relay.service.ChannelService;
import relay.service.MessageService;

@RestController
@RequestMapping("/channels/{channelName}/messages")
public class MessageController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MessageService messageService;

    @GetMapping
    public Flux<Message> getMessages(@PathVariable String channelName) {
        return messageService.getMessages(channelService.findByName(channelName));
    }

    @PostMapping
    public void createMessage(@PathVariable String channelName, @RequestBody CreateMessageRequest message, @AuthenticationPrincipal Mono<User> user) {
        channelService.findByName(channelName)
                .subscribe(channel -> user.subscribe(u -> messageService.createMessage(u, channel, message)));
    }

}
