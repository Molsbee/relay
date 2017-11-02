package relay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsRepository;
import relay.model.User;
import relay.repository.UserRepository;

import javax.annotation.PostConstruct;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void populateUser() {
        userRepository.findById("admin")
                .defaultIfEmpty(new User("admin", "admin123"))
                .map(u -> userRepository.save(u).subscribe())
                .subscribe();
    }

    @Bean
    public UserDetailsRepository userDetailsRepository() {
        return s -> userRepository.findById(s).map(u -> (UserDetails) u);
    }

}
