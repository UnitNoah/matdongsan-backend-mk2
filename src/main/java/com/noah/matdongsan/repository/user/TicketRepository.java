package com.noah.matdongsan.repository.user;

import com.noah.matdongsan.entity.user.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("SELECT t.amount FROM Ticket t WHERE t.commonUser.id = :userId AND t.amount > 0")
    Integer getTicketAmountByUserId(@Param("userId") Long userId);

}
