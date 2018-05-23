package relay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import relay.model.Channel;
import relay.model.Message;
import relay.model.User;
import relay.model.web.CreateMessageRequest;
import relay.repository.MessageRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Flux<Message> getMessages(Mono<Channel> channel) {
        return messageRepository.findByChannel(channel);
    }

    public void createMessage(User user, Channel channel, CreateMessageRequest request) {
        messageRepository.save(Message.builder()
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .message(request.getMessage())
                .channel(channel)
                .build()
        ).subscribe(
                m -> log.info("create message {} in channel {}", request.getMessage(), channel.getName()),
                e -> log.error("failed to save message in channel {}", channel.getName())
        );
    }

}
