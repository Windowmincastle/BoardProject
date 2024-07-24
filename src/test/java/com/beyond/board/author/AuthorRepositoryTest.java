package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.repository.AuthorRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional//롤백처리를 위해 Transactional 어노테이션 사용
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest(){
        // 테스트 원리 save -> 재조회 -> 저장할때 만든 객체와 비교
        // 준비 ( prepare , given ) 단계
        Author author = Author.builder()
                .name("4222kim")
                .email("asdjjki22@naver.com")
                .password("1234") // Service 단에서 검증을 하고있기 떄문에 여기선 비밀번호 오류 안남.
                .role(Role.ADMIN)
                .build();

        authorRepository.save(author);

//        실행 ( execute, when )
        Author savedauthor = authorRepository.findByEmail("asdjjki22@naver.com").orElse(null);

//        검증(then) 저장하고 가져와서 비교해보면 제대로 db에 저장 됐는지 테스트 해볼 수 있겟지?
        Assertions.assertEquals(author.getEmail(), savedauthor.getEmail()); // 에러가 터지면 어쨋든 문제가 잇는 것.

    }

}
