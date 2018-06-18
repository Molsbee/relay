package relay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import relay.model.User;
import relay.model.web.UserRequest;
import relay.model.web.UserResponse;
import relay.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(params = {"self"})
    public Flux<UserResponse> get(@RequestParam("self") Boolean self, @AuthenticationPrincipal User user) {
        if (self) {
            return Flux.from(
                    userService.findByName(user.getUsername())
            ).map(UserResponse::new);
        }

        return userService.findAll().map(UserResponse::new);
    }

    @PostMapping
    public void create(@RequestBody UserRequest userRequest) {
        userService.create(userRequest.convertToUser());
    }

    @PutMapping
    @RequestMapping("/{userName}")
    public void subscribeToChannel(@PathVariable String username, @RequestBody String channelName) {
        userService.findByName(username).subscribe(
                u -> userService.subscribeToChannel(u, channelName)
        );
    }

}
