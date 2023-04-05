package com.vpos.server.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ListIterator;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
           User bill =  new User(

                   "biLL",
                   "Skun",
                   "bill.skun@pathmizing.com",
                   "123456",
                   true,
                   List.of(1, 2),
                   true,
                   List.of("admin", "user")
           );

            User marina =  new User(

                    "Marina",
                    "SC",
                    "marina.sc@pathmizing.com",
                    "123456",
                    true,
                    List.of(1, 2),
                    false,
                    List.of("admin", "user")
            );

            repository.saveAll(
                    List.of(bill, marina)
            );
        };
    }
}
