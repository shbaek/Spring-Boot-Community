package com.web.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardsResponseDto {

    private Long idx;
    private String title;
    private String subTitle;
    private String content;
    private com.community.rest.domain.enums.BoardType boardType;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private User user;

    public BoardsResponseDto(Board entity) {
        this.idx = entity.getIdx();
        this.title = entity.getTitle();
        this.subTitle = entity.getSubTitle();
        this.content = entity.getContent();
        this.boardType = entity.getBoardType();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getUpdatedDate();
        this.user = entity.getUser();
    }
}
