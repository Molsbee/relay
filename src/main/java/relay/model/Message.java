package relay.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

public class Message {

    @Id
    private String id;

    @DBRef
    private User createdBy;

    private LocalDateTime createdAt;

    private String message;

    @DBRef
    private Channel channel;

}
