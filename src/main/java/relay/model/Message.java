package relay.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Message {

    @Id
    private String id;

    private User createdBy;

    private LocalDateTime createdAt;

    private String message;

    private Channel channel;

}
