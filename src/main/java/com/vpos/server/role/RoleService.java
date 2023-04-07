package com.vpos.server.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> findOneRoleById(Long id) {
       return roleRepository.findById(id);
    }

    public void deleteOneRole(Long id) {
        roleRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("The role id " + id + " does not exist");
        });
        roleRepository.deleteById(id);
    }


    public Role createOneRole(Role role) {
        if(!StringUtils.hasText(role.getRoleName())) {
            throw new IllegalStateException("The role name is required.");
        }

       Optional<Role> _role = roleRepository.findRoleByName(role.getRoleName());
        if(_role.isPresent()){
            throw new IllegalStateException("The role name " + role.getRoleName() + "already exists.");
        }

       return roleRepository.save(role);
    }

    @Transactional
    public Role updateOneRole(Long roleId, Role role) {
        Role _role = roleRepository.findById(roleId).orElseThrow(() -> {
            throw new IllegalStateException("The role id" + roleId + " does not exists.");
        });

        Optional<Role> exist = roleRepository.findRoleByName(role.getRoleName());

        if(exist.isPresent()) throw new IllegalStateException("The role name " + role.getRoleName() + " already exists.");

        _role.setRoleName(role.getRoleName());

      return roleRepository.save(_role);
    }
}
