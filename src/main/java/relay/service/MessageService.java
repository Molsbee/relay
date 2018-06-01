package relay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.CappedMessage;
import relay.model.Channel;
import relay.model.Message;
import relay.model.User;
import relay.model.web.CreateMessageRequest;
import relay.repository.CappedMessageRepository;
import relay.repository.MessageRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CappedMessageRepository cappedMessageRepository;

    public Flux<Message> getMessages(Mono<Channel> channel) {
        return messageRepository.findByChannel(channel);
    }

    public Flux<CappedMessage> getMessageStream(Mono<Channel> channel) {
        return cappedMessageRepository.findByChannel(channel);
    }

    public void createMessage(User user, Channel channel, CreateMessageRequest request) {
        Message message = Message.builder()
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .message(request.getMessage())
                .channel(channel)
                .build();

        messageRepository.save(message).subscribe(
                m -> {
                    log.info("user {} created message in channel {}", user.getUsername(), channel.getName());
                    cappedMessageRepository.save(m.toCappedMessage()).subscribe(
                            c -> log.info("user {} created capped message in channel {}", user.getUsername(), channel.getName()),
                            e -> log.error("failed to save user {} capped message for channel {}", user.getUsername(), channel.getName())
                    );
                },
                e -> log.error("failed to save user {} message for channel {}", user.getUsername(), channel.getName())
        );
    }

}
