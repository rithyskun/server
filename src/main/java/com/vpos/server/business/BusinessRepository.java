package com.vpos.server.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findBusinessByName(String name);

    Business findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Business WHERE id =:id AND is_active=false")
    void findBusinessByIdAndStatus(Long id);

}
