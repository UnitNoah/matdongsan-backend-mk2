package com.noah.matdongsan.repository.user;

import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
