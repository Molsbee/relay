package relay.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@NoArgsConstructor
public class Channel {

    @Id
    private String name;
    private User createdBy;
    private LocalDateTime createdAt;

    public Channel(String name, User createdBy, LocalDateTime dateTime) {
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = dateTime;
    }

}
