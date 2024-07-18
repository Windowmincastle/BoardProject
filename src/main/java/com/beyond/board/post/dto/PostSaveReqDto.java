package com.beyond.board.post.dto;


import com.beyond.board.author.domain.Author;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostSaveReqDto {

    private String title;
    private String contents;
    private String email; // 글쓴이가 누군지 알려면 입력받는 식 으로 하겟다. 그런데 로그인 처리를 하면
    // 17라인처럼 할 필요가 없겟지? 로그인 세션에서 꺼내올거니까 ㅇㅋ?

    // 저장할 데이터 보내야겟지? ㅇㅋ?
    public Post toEntity(Author author){
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .build();
    }

}
