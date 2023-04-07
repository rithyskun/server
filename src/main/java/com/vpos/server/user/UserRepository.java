package com.vpos.server.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT s FROM User s WHERE LOWER(s.email) LIKE %?1%")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT s FROM User s WHERE s.email LIKE %?1%")
    List<User> findUserEmailContaining(String email);

    @Query("SELECT s FROM User s WHERE s.firstname LIKE %?1%")
    List<User> findUserContainingName(String name);

    @Query("SELECT s FROM User s WHERE s.id=?1 AND s.status=?1")
    Long findUserByIdAndStatus(Long id);

}
