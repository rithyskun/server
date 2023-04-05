package com.vpos.server.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT s FROM User s WHERE s.id=?1 AND s.status=?1")
    Long findUserByIdAndStatus(Long id);

//    @Modifying
//    @Query("UPDATE User u SET u.firstname=?, u.lastname=?, u.status=?, u.email=?, u.roles=? WHERE u.id=?1")
//    void updateUser(Long id, User user);
}
