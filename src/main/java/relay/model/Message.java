package relay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "message")
public class Message {

    @Id
    String id;

    @DBRef
    User createdBy;

    LocalDateTime createdAt;

    String message;

    @DBRef
    Channel channel;

    public CappedMessage toCappedMessage() {
        return CappedMessage.builder()
                .createdBy(createdBy)
                .createdAt(createdAt)
                .message(message)
                .channel(channel)
                .build();
    }

}
