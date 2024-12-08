package com.noah.matdongsan.service.property;

import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.entity.user.Ticket;
import com.noah.matdongsan.repository.property.PropertyRepository;
import com.noah.matdongsan.repository.user.CommonUserRepository;
import com.noah.matdongsan.repository.user.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final TicketRepository ticketRepository;
    private final CommonUserRepository commonUserRepository;

    public void updateTicketAmount(String email, int amount) {
        // 1. CommonUser 조회
        CommonUser commonUser = commonUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        int price = calculatePrice(amount);

        Ticket ticket = new Ticket(amount, price, commonUser);
        ticketRepository.save(ticket);
    }

    private int calculatePrice(int amount) {
        return switch (amount) {
            case 1 -> 5500;
            case 3 -> 15000;
            case 5 -> 25000;
            default -> throw new IllegalArgumentException("Invalid amount: " + amount);
        };
    }
}
