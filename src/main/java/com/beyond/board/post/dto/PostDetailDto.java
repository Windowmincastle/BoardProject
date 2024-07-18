package com.beyond.board.post.dto;


import com.beyond.board.post.domain.Post;
import com.beyond.board.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDetailDto {

    private Long id;
    private String title;
    private String contents;
    private String author_emai;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;


}
