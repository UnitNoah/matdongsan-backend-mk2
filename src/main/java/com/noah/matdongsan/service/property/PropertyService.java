package com.noah.matdongsan.service.property;

import com.noah.matdongsan.dto.property.PropertyCreateDto;
import com.noah.matdongsan.dto.property.PropertyReadDto;
import com.noah.matdongsan.dto.property.PropertyUserDto;
import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.property.PropertyDetail;
import com.noah.matdongsan.entity.property.PropertyPhoto;
import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.entity.user.Ticket;
import com.noah.matdongsan.repository.property.PropertyRepository;
import com.noah.matdongsan.repository.user.TicketRepository;
import com.noah.matdongsan.service.common.FileUploadService;
import com.noah.matdongsan.service.member.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                        .pnumber(property.getId())
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

    @Transactional(readOnly = true)
    public PropertyUserDto getUserDataByPropertyId(Long propertyId) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isEmpty()) {
            throw new EntityNotFoundException("Property not found with id: " + propertyId);
        }
        CommonUser commonUser = propertyOptional.get().getCommonUser();
        return PropertyUserDto.builder()
                .uid(commonUser.getId())
                .uname(commonUser.getName())
                .urole(commonUser.getRole())
                .uphone(commonUser.getPhone())
                .isRemoved(commonUser.isRemoved())
                .profileUrl(commonUser.getProfileUrl())
                .uemail(commonUser.getEmail())
                .build();
    }

    // return 값 줘야하고 DTO 하나 만들어서 프론트로 전달해야함
    // 모든 매물 데이터를 넘기는게 맞나?
    // 하나씩 각 컴포넌트에 맞게 보내면 되지 않나?
    @Transactional(readOnly = true)
    public void getPropertyByPropertyId(Long propertyId) {
        Optional<PropertyDetail> propertyDetail = propertyDetailService.getDetailById(propertyId);
        List<PropertyPhoto> photos = propertyPhotoService.getPhotosById(propertyId);
        Optional<Property> property = propertyRepository.findById(propertyId);

    }
}
