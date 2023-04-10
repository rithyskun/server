package com.vpos.server.role;

/*
 * @created 10/04/2023 - 2:27 PM
 * @project server
 * @author Rithy SKUN
 */

import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface RoleService {
    Collection<Role> getRoles();
    Role createRole(Role role);

    void deleteRole(Long id);
    Role updateRole(Long id, Role role);

}
