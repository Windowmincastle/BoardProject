package com.beyond.board.post.domain;
import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostDetailDto;
import com.beyond.board.post.dto.PostListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 3000, nullable = false)
    private String contents;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public PostListResDto fromEntity(){
        PostListResDto postListResDto = PostListResDto.builder()
                .id(this.id)
                .title(this.title)
                .author_email(this.author.getEmail())//여기 한번 체크해야함
                .build();

        return postListResDto;
    }

    public PostListResDto listFromEntity(){
        return PostListResDto.builder()
                .id(this.id)
                .title(this.title)
                .author_email(this.author.getEmail())
                .build();
    }

    public PostDetailDto detFromEntity(){

        return PostDetailDto.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .createdTime(this.getCreatedTime()) // baseentity로 상속하고 사용하려고 햇는데 오류가 생김
                .updatedTime(this.getUpdateTime()) // getter로 대체하라고 나온다 private이기 때문에 직접 접근이 불가능하다.
                .build();                      // 그래서 getter로 접근하라고 나오는 것 같다. 근데 추상클래스로 바꾸면 된다
    }

}
