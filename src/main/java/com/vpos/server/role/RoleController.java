package com.vpos.server.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<List<Role> > getRoles() {
       return ResponseEntity.ok(roleService.findRoles());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.createOneRole(role));
    }

    @PutMapping(path = "{roleId}")
    public ResponseEntity<Role> updateRoleById(@PathVariable("roleId") Long id, @RequestBody Role role) {
        return ResponseEntity.ok(roleService.updateOneRole(id, role));
    }

    @DeleteMapping(path = "{roleId}")
    public ResponseEntity<Map<String, Boolean>> deleteRoleById(@PathVariable("roleId") Long id) {
        roleService.deleteOneRole(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("The role id " + id + " has been removed", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
