package com.vpos.server.migration;

import com.vpos.server.business.Business;
import com.vpos.server.business.BusinessService;
import com.vpos.server.role.Role;
import com.vpos.server.role.RoleService;
import com.vpos.server.user.User;
import com.vpos.server.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class Database implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    private final BusinessService businessService;

    @Autowired
    public Database( UserService userService, RoleService roleService, BusinessService businessService) {
        this.userService = userService;
        this.roleService = roleService;
        this.businessService = businessService;
    }

    @Override
    public void run(String... args) throws Exception {

        businessService.createBusiness(new Business("vStore", "PP", "PP1", true));
        businessService.createBusiness(new Business("xStore", "PP", "PP1", false));

        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.createRole(new Role("VIEWER"));

        userService.registerUser(new User("Rithy", "SKUN", "rithy.skun@outlook.com", "12345678", true, new ArrayList<>(), true, new ArrayList<>(), new Date(), new Date() ));
        userService.registerUser(new User("bill", "mr", "bill.mr@outlook.com", "12345678", false, new ArrayList<>(), true, new ArrayList<>(), new Date(), new Date() ));

        userService.addRoleToUser("rithy.skun@outlook.com", "ADMIN");
        userService.addRoleToUser("bill.mr@outlook.com", "USER");


    }
}
