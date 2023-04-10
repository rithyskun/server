package com.vpos.server.role;

/*
 * @created 10/04/2023 - 2:26 PM
 * @project server
 * @author Rithy SKUN
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        Role _role = roleRepository.findByRoleName(role.getRoleName());

        if(_role != null) {
            throw new IllegalStateException("Role name " + role.getRoleName() + " already exists.");
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("Role id " + id + " does not exists");
        });

        roleRepository.deleteById(id);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Role _role = roleRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("The role id" + id + " does not exists.");
        });

        Role exist = roleRepository.findByRoleName(role.getRoleName());

        if(exist != null) throw new IllegalStateException("The role name " + role.getRoleName() + " already exists.");

        _role.setRoleName(role.getRoleName());

        return roleRepository.save(_role);
    }
}
