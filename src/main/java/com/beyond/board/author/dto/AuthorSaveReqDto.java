package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

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


//    안되는 것 같아서 주석처리햇음
//    //필드 주입 방식으로
//    @Autowired //엔티티에서 DI가 되는건가?라고 찾아보며 수업햇음
//    //bean을 주입받으려면 bean에다가만 할수있다는 것 같다.
//    private PasswordEncoder passwordEncoder;

//    //  객체 -> Entity로 toEntity 보내서 저장하기.
    // 암호화된 패스워드 받겟다고 매개변수 추가.
    public Author toEntity(String encodedPassword){
        // SaveReqDto 객체로 요청받은 데이터를 author 객체에 담아서 DB에 저장해야겟지?
        // 그러니까 여기서 조립을 해야해
        Author author = Author.builder()
                .password(encodedPassword)
                .name(this.name)
                .email(this.email)
                .posts(new ArrayList<>())
                .role(this.role).build();

        return author;
    }

//    public Author toEntity(){
//        Author author = Author.builder().password(this.password).name(this.name)
//                .email(this.email).role(this.role)
//                .posts(new ArrayList<>())
//                .build();
//        return author;
//    }



}
