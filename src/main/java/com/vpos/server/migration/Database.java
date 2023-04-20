package com.vpos.server.migration;

import com.vpos.server.business.Business;
import com.vpos.server.business.BusinessService;
import com.vpos.server.business.BusinessServiceImpl;
import com.vpos.server.category.Category;
import com.vpos.server.category.CategoryService;
import com.vpos.server.product.ProductService;
import com.vpos.server.role.Role;
import com.vpos.server.role.RoleService;
import com.vpos.server.upload.storage.FileUploadLocalStorageService;
import com.vpos.server.user.User;
import com.vpos.server.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Component
public class Database implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final BusinessService businessService;
    private final CategoryService categoryService;
    private final ProductService productService;

    private final FileUploadLocalStorageService fileUploadLocalStorageService;

    @Autowired
    public Database(UserService userService, RoleService roleService, BusinessServiceImpl businessServiceImpl, PasswordEncoder passwordEncoder, BusinessService businessService, CategoryService categoryService, ProductService productService, FileUploadLocalStorageService fileUploadLocalStorageService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.businessService = businessService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileUploadLocalStorageService = fileUploadLocalStorageService;
    }

    @Override
    public void run(String... args) throws Exception {

        fileUploadLocalStorageService.init();

        businessService.createBusiness(new Business("vStore", "PP", "PP1", true, LocalDateTime.now(), LocalDateTime.now()));
        businessService.createBusiness(new Business( "xStore", "PP", "PP1", false, LocalDateTime.now(), LocalDateTime.now()));

        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.createRole(new Role("VIEWER"));

        userService.registerUser(new User("Rithy", "SKUN", "rithy.skun@outlook.com", passwordEncoder.encode("123456789"), true, new ArrayList<>(), true, new ArrayList<>(), new Date(), new Date() ));
        userService.registerUser(new User("bill", "mr", "bill.mr@outlook.com", passwordEncoder.encode("123456"), false, new ArrayList<>(), true, new ArrayList<>(), new Date(), new Date() ));

        userService.addRoleToUser("rithy.skun@outlook.com", "ADMIN");
        userService.addRoleToUser("bill.mr@outlook.com", "USER");

        userService.addBusinessToUser("rithy.skun@outlook.com", "vStore");
        userService.addBusinessToUser("bill.mr@outlook.com", "xStore");

        categoryService.createCategory( new Category("category-1", "http:///kjadsfjlkad", true));
        categoryService.createCategory( new Category("category-2", "http:///kjadsfjlkad", true));

//        productService.createProduct(new Product("Fry noodle", "description", 12.5, 1L, "barcode", "variant", "sku", "brand-1", 1L, true, true, false, false, true, 0,0, ProductType.Single, "httpljadskfljladsf" ));
    }
}
