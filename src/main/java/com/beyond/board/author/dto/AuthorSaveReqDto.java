package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveReqDto {

    //왜 굳이 saveRequestDto까지 있어야할까? 그럼 id나 current time, updated time 같은 데이터를 회원이 어떻게 일일이 입력하겟냐?
    private String name;
    private String email;
    private String password;
    // 사용자가 String으로 요청해도 Role 클래스로 자동 형변환이 잘 되더라
    private Role role;

    //  객체 -> Entity로 toEntity 보내서 저장하기.
    public Author toEntity(){
        // SaveReqDto 객체로 요청받은 데이터를 author 객체에 담아서 DB에 저장해야겟지?
        // 그러니까 여기서 조립을 해야해

        Author author = Author.builder()
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .role(this.role).build();

        return author;
    }



}
