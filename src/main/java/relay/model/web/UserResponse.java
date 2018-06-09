package relay.model.web;

import lombok.Data;
import relay.model.Channel;
import relay.model.User;

import java.util.List;

@Data
public class UserResponse {

    private String username;
    private String firstName;
    private String lastName;
    private List<Channel> subscribedChannels;

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.subscribedChannels = user.getSubscribedChannels();
    }

}
