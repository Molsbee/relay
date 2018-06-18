package relay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.User;
import relay.model.web.CreateMessageRequest;
import relay.model.web.MessageResponse;
import relay.service.ChannelService;
import relay.service.MessageService;

@Slf4j
@RestController
@RequestMapping(value = "/api/channels/{channelName}/messages")
public class MessageController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MessageService messageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux getMessages(@PathVariable String channelName) {
        log.info("attempt to retrieve messages for channel {}", channelName);
        return messageService.getMessages(channelService.findByName(channelName))
                .map(MessageResponse::new);
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux getMessageStream(@PathVariable String channelName) {
        log.info("attempting to retrieve message stream for channel {}", channelName);
        return messageService.getMessageStream(channelService.findByName(channelName))
                .map(MessageResponse::new);
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> createMessage(@PathVariable String channelName, @RequestBody CreateMessageRequest message, @AuthenticationPrincipal User user) {
        log.info("user {} creating message in channel {}", user.getUsername(), channelName);
        return channelService.findByName(channelName)
                .flatMap(c -> {
                    messageService.createMessage(user, c, message);
                    return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
