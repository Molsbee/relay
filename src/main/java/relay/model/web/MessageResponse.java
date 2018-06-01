package relay.model.web;

import lombok.Data;
import relay.model.CappedMessage;
import relay.model.Message;

import java.time.LocalDateTime;

@Data
public class MessageResponse {

    private String message;
    private String createdBy;
    private LocalDateTime createdAt;

    public MessageResponse(Message message) {
        this.message = message.getMessage();
        this.createdBy = message.getCreatedBy().getUsername();
        this.createdAt = message.getCreatedAt();
    }

    public MessageResponse(CappedMessage cappedMessage) {
        this.message = cappedMessage.getMessage();
        this.createdBy = cappedMessage.getCreatedBy().getUsername();
        this.createdAt = cappedMessage.getCreatedAt();
    }

}
