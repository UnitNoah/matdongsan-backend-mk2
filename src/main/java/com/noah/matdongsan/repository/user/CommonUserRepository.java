package com.noah.matdongsan.repository.user;

import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.user.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser,Long> {
    @Modifying
    @Query("UPDATE CommonUser u SET u.isRemoved = true WHERE u.id = :id")
    int softDeleteById(@Param("id") Long id);
}
