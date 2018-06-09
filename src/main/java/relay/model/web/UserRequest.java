package relay.model.web;

import lombok.Data;
import relay.model.User;

@Data
public class UserRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User convertToUser() {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .build();
    }

}
