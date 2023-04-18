package com.vpos.server.user;

import com.vpos.server.business.Business;
import com.vpos.server.role.Role;

import java.util.List;

/**
 * @author Rithy SKUN
 * @created 17/04/2023 - 7:54 AM
 * @project server
 **/

public record UserResponse (
        Long id,
        String firstname,
        String lastname,
        String email,
        Boolean status,

        List<Role> roles,
        List<Business> businesses
) {

}
