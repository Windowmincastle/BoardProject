package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 3000)
    private String contents;

    // 연관 관계의 주인은 FK 가 있는 Post.
    @ManyToOne(fetch = FetchType.LAZY) //참조 안 하면 안 나가게.
    @JoinColumn(name = "author_id")
    private Author author;

    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    private String appointment;
    private LocalDateTime appointmentTime; //String으로 받아서 형변환


    public PostListResDto listFromEntity() {
        return PostListResDto.builder()
                .id(this.id)
                .title(this.title)
                .author_email(author.getEmail())
                .build();
    }

    public PostDetResDto detFromEntity(){
        return PostDetResDto.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .author_email(this.author.getEmail())
                .createdTime(this.getCreatedTime())
                .updatedTime(this.getUpdateTime())
                .build();
    }

    public void updatePost(PostUpdateReqDto postUpdateReqDto) {
        this.title = postUpdateReqDto.getTitle();
        this.contents = postUpdateReqDto.getContents();
    }

    public void updateAppointment(String yn) {
        this.appointment = yn;
    }
}
