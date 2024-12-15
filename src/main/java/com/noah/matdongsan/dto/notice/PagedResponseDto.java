package com.noah.matdongsan.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedResponseDto<T> {
    private List<T> content;        // 데이터 리스트
    private int currentPage;        // 현재 페이지 번호
    private int totalPages;         // 전체 페이지 수
    private long totalElements;     // 전체 데이터 개수
    private boolean isFirst;        // 첫 페이지 여부
    private boolean isLast;         // 마지막 페이지 여부
}
