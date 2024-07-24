package com.beyond.board.post.dto;


import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostListResDto {

    private Long id;
    private String title;
    private String author_email;

}
