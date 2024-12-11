package com.noah.matdongsan.service.notice;

import com.noah.matdongsan.dto.notice.CustomerQuestionCreateDto;
import com.noah.matdongsan.entity.notice.Question;
import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.repository.notice.QuestionRepository;
import com.noah.matdongsan.repository.user.CommonUserRepository;
import com.noah.matdongsan.service.common.FileUploadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    @Lazy
    private final FileUploadService fileUploadService;

    private final QuestionRepository questionRepository;

    private final CommonUserRepository commonUserRepository;

    public void createCustomerInquiry(CustomerQuestionCreateDto dto) throws IOException {

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageUrl = fileUploadService.uploadFile(dto.getImage());
            dto.updateImageUrl(imageUrl);
        }
        Optional<CommonUser> commonUser = commonUserRepository.findByEmail(dto.getEmail());

        if (commonUser.isPresent()) {
            Question question = Question.builder()
                    .category(dto.getCategory())
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .commonUser(commonUser.get()) // 데이터 사용
                    .build();
            questionRepository.save(question);
        } else {
            throw new EntityNotFoundException("유저를 찾을 수 없습니다: " + dto.getEmail());
        }
    }


}
