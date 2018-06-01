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
@Document(collection = "capped_message")
public class CappedMessage {

    @Id
    String id;

    @DBRef
    User createdBy;

    LocalDateTime createdAt;

    String message;

    @DBRef
    Channel channel;

}
