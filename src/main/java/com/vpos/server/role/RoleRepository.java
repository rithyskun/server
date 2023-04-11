package com.vpos.server.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT s FROM Role s WHERE s.roleName = :roleName")
    Role findByRoleName(String roleName);

}
