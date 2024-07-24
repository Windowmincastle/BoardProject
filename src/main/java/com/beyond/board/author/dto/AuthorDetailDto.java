package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDetailDto extends BaseTimeEntity {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private int postCounts;
    private LocalDateTime createdTime; // rest api 형식으로 하면 그냥 시간 같이 던져주고


    // 프론트엔드 개발자한테 알아서 해라 , 이렇게 써도 되고 무방하고, Author 클래스에
    // 선언해서 사용해도 무방하다.
    public AuthorDetailDto fromEntity(Author author){
        AuthorDetailDto authorDetailDto = AuthorDetailDto.builder()
                .id(author.getId())
                .name(author.getName()).email(author.getEmail())
                .password(author.getPassword()).createdTime(author.getCreatedTime())
                .role(author.getRole()).postCounts(author.getPosts().size())
                .build();
        return authorDetailDto;
    }

}
