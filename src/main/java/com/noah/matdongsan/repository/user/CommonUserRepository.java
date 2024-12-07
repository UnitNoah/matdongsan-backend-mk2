package com.noah.matdongsan.repository.user;

import com.noah.matdongsan.entity.user.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser,Long> {
    @Modifying
    @Query("UPDATE CommonUser u SET u.isRemoved = true WHERE u.id = :id")
    int softDeleteById(@Param("id") Long id);

    Optional<CommonUser> findByEmail(String email);

    Optional<CommonUser> findByNameAndPhone(String name, String phone);

    @Query("SELECT u.id FROM CommonUser u WHERE u.email = :email")
    Optional<Long> findUserIdByEmail(@Param("email")String email);
}
