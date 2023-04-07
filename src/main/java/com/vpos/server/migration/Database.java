package com.vpos.server.migration;

import com.github.javafaker.Faker;
import com.vpos.server.business.Business;
import com.vpos.server.business.BusinessRepository;
import com.vpos.server.role.Role;
import com.vpos.server.role.RoleRepository;
import com.vpos.server.user.User;
import com.vpos.server.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Database implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BusinessRepository businessRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public Database(UserRepository userRepository, BusinessRepository businessRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@email.com", firstName, lastName);
        Number number = faker.number().numberBetween(10, 23);

        String storeName = faker.name().name();
        String address = faker.address().fullAddress();
        String address1 = faker.address().cityPrefix();

        //Create Business brand
        Business business = new Business(
                "vStore", "address", "address 1",
                true
        );
        Business business1 = new Business(
                "xStore", "address 123", "address 567",
                true
        );

        businessRepository.saveAll(List.of(business, business1));

        Business busId = businessRepository.getReferenceById(1L);

        Role admin = new Role(
                "Admin"
        );

        Role user = new Role(
                "User"
        );

        roleRepository.saveAll(List.of(admin, user));

        Role roleId = roleRepository.getReferenceById(1L);

        //Create User
        User bill =  new User(
                "Rithy",
                "SKUN",
                "rithy.skun@outlook.com",
                "1234567890",
                true,
                List.of(busId),
                true,
                List.of(roleId, roleId),
                new Date(),
                new Date()
        );


        User marina =  new User(
                firstName,
                lastName,
                email,
                "123456",
                false,
                List.of(busId, busId),
                true,
                List.of(roleId, roleId),
                new Date(),
                new Date()
        );
        userRepository.saveAll(List.of(bill, marina));

    }
}
