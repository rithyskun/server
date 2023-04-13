package com.vpos.server.role;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<Collection<Role>> getRoles() {
       return ResponseEntity.ok(roleService.getRoles());
    }

    @PostMapping()
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
        return ResponseEntity.created(uri).body(roleService.createRole(role));
    }

    @PutMapping(path = "{roleId}")
    public ResponseEntity<Role> updateRoleById(@PathVariable("roleId") Long id, @RequestBody Role role) {
        return ResponseEntity.ok(roleService.updateRole(id, role));
    }

    @DeleteMapping(path = "{roleId}")
    public ResponseEntity<Map<String, Boolean>> deleteRoleById(@PathVariable("roleId") Long id) {
        roleService.deleteRole(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("The role id " + id + " has been removed", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
