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
//  Author 객체 자체를 return 하게 되면 Author 안에 Post가 재참조되어, 순환참조 이슈가 발생한다.
//    private Author author; 순환참조 테스트하면서 사용햇엇다.
    private String author_email;

}
