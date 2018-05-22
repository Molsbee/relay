package relay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import relay.model.User;
import relay.repository.UserRepository;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void populateUser() {
        userRepository.findById("admin")
                .defaultIfEmpty(User.builder().username("admin").password(passwordEncoder().encode("admin123")).build())
                .map(u -> userRepository.save(u).subscribe())
                .subscribe();
    }

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .anyExchange().authenticated()
                .and().httpBasic()
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return username -> userRepository.findById(username).cast(UserDetails.class);
    }

}
