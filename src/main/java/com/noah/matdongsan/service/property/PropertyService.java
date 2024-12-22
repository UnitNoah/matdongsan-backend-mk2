package com.noah.matdongsan.service.property;

import com.noah.matdongsan.dto.property.PropertyCreateDto;
import com.noah.matdongsan.dto.property.PropertyReadDto;
import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.entity.user.Ticket;
import com.noah.matdongsan.repository.property.PropertyRepository;
import com.noah.matdongsan.repository.user.TicketRepository;
import com.noah.matdongsan.service.common.FileUploadService;
import com.noah.matdongsan.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final TicketRepository ticketRepository;
    private final MemberService memberService;
    private final FileUploadService fileUploadService;
    private final PropertyDetailService propertyDetailService;
    private final PropertyPhotoService propertyPhotoService;

    @Transactional
    public void updateTicketAmount(String email, int amount) {
        CommonUser commonUser = memberService.findUserByEmail(email);

        int price = calculatePrice(amount);

        Ticket ticket = new Ticket(amount, price, commonUser);
        ticketRepository.save(ticket);
    }

    //생성
    @Transactional
    public void createProperty(PropertyCreateDto dto, String email) {

        CommonUser commonUser = memberService.findUserByEmail(email);

        Property property = Property.builder()
                .deposit(dto.getPdeposite())
                .monthlyFee(dto.getPrentalfee())
                .maintenance(dto.getPmaintenance())
                .floorType(dto.getPfloortype())
                .floor(dto.getPfloor())
                .roomSize(dto.getPsize())
                .title(dto.getPtitle())
                .category(dto.getPcategory())
                .address(dto.createAddress())
                .commonUser(commonUser)
                .build();

        if (dto.getPthumbnail() != null && !dto.getPthumbnail().isEmpty()) {
            String thumbnailUrl = fileUploadService.uploadFile(dto.getPthumbnail());
            property.updateThumbnailUrl(thumbnailUrl);
        }

        propertyRepository.save(property);
        propertyDetailService.create(dto.getPropertyDetail(), property);
        propertyPhotoService.create(dto.getPpattach(), property);
    }


    private int calculatePrice(int amount) {
        return switch (amount) {
            case 1 -> 5500;
            case 3 -> 15000;
            case 5 -> 25000;
            default -> throw new IllegalArgumentException("Invalid amount: " + amount);
        };
    }

    public Page<PropertyReadDto> getProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable)
                .map(property -> PropertyReadDto.builder()
                        .pdeposite(property.getDeposit())
                        .prentalfee(property.getMonthlyFee())
                        .pfloortype(property.getFloorType())
                        .pfloor(property.getFloor())
                        .psize(property.getRoomSize())
                        .pmaintenance(property.getMaintenance())
                        .ptitle(property.getTitle())
                        .pcategory(property.getCategory())
                        .pthumbnail(property.getThumbnailUrl())
                        .paddress(property.getAddress())
                        .status(property.getStatus())
                        .build()
                );
    }

}
