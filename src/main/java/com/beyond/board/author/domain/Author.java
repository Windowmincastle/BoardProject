package com.beyond.board.author.domain;


import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.domain.Post;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// Data는 떼라
// Builder 어노테이션을 통해 매개변수의 순서, 매개변수의 개수 등을 유연하게 세팅하여 생성자로서 활용한다
@Builder // AllArgs 없으면 오류남
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@AllArgsConstructor // 이거 넣고 작업햇을거 같은데 뺴고
public class Author extends BaseTimeEntity{

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMent 설정
    private Long id;
    @Column(length=20, nullable = false) // 컬럼 단위로 설정을 해줄 수 있다.
    private String name;
    @Column(length=20, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "author") // 일대다
    private List<Post> posts;


// 7.18일 : 빌더패턴으로 아래 예시처럼 세팅해서 유연하게 생성자를 만들 수 있다. 이거 후에 빌더패턴으로 넘어갓음
//    @Builder
//    public Author(String name,String email,String password) {
//
//        this.name=name;
//        this.email=email;
//        this.password = password;
//
//
//    }

    public AuthorListResDto fromEntity(){

        AuthorListResDto authorListResDto = AuthorListResDto.builder()
                .id(this.id)
                .email(email)
                .name(email).build();

        return authorListResDto;

    }

    //    this가 아니라 매개변수로 받아서 조립하겟다
    public AuthorDetailDto detailFromEntity() {

        LocalDateTime createdTime = this.getCreatedTime();
        String date = createdTime.getYear() + "년 " + createdTime.getMonthValue() + "월 " +
                createdTime.getDayOfMonth() + "일";

        //AuthorDetailDto authorDetailDto = new AuthorDetailDto(this.id, this.name, this.email, this.password, date);
        // builder로 생성
        AuthorDetailDto authorDetailDto = AuthorDetailDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .postCounts(this.posts.size())
                .createdTime(this.getCreatedTime())
                .build();

        return authorDetailDto;
    }




}
