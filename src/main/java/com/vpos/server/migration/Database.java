package com.vpos.server.migration;

import com.vpos.server.business.Business;
import com.vpos.server.business.BusinessRepository;
import com.vpos.server.user.User;
import com.vpos.server.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Database implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BusinessRepository businessRepository;

    @Autowired
    public Database(UserRepository userRepository, BusinessRepository businessRepository) {
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Create User
        User bill =  new User(

                "Rithy",
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
        userRepository.saveAll(List.of(bill, marina));

        //Create Business brand
        Business business = new Business(
                "vPOS Store",
                "PP",
                "PP",
                true,
                1
        );
        Business business1 = new Business(
                "Store 1",
                "PP1",
                "PP1",
                false,
                1
        );

        businessRepository.saveAll(List.of(business, business1));
    }
}
