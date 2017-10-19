package relay.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Channel {

    @Id
    private String name;
    private User createdBy;
    private LocalDateTime createAt;

}
