package com.beyond.board.post.dto;

import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateReqDto {

    private String title;
    private String contents;

    public PostUpdateReqDto toEntity(Post post){
        PostUpdateReqDto postUpdateReqDto = PostUpdateReqDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .build();
        return postUpdateReqDto;
    }
}
