package com.vpos.server.role;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        return ResponseEntity.ok(roleService.createRole(role));
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
