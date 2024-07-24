package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;


@SpringBootTest
@Transactional
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void saveAndFind(){
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto(
                "hong", "hong@daum.net","12341234", Role.ADMIN // 비밀번호 예외 처리 잇음.
        );

        Author author = authorService.authorCreate(authorDto);
//        Author authorDetail = authorService.authorFindByEmail("hong@daum.net"); //이메일 유니크 검증
        Author authorDetail = authorRepository.findById(author.getId()).orElseThrow(
                () -> new EntityNotFoundException("not found")
        ); // 이메일로 유니크하게 검증하려했는데 ID로 검증하는게 가장 확실하다.

        //검증
        Assertions.assertEquals(author.getEmail(), authorDto.getEmail()); // 에러가 터지면 어쨋든 문제가 잇는 것.

    }

    // update 검증
    // 객체 create
    // 수정 작업
    // 수정 후 재조회 : name, password가 맞는지 각각 어설설트어설머시기로 검증
    @Test
    void createAndUpdate(){

        AuthorSaveReqDto authorSaveReqDto = new AuthorSaveReqDto(
                "hong111", "hong111@daum.net","12341234", Role.ADMIN // 비밀번호 예외 처리 잇음.
        );

        Author author = authorService.authorCreate(authorSaveReqDto);
        authorService.authorUpdate(
                author.getId(),new AuthorUpdateReqDto("hong", "43214321"));

        // pk로 조회하는게 100프로가 아니겟냐?
        Author savedAuthor = authorService.authorFindByEmail("hong111@daum.net");

        Assertions.assertEquals("hong", savedAuthor.getName()); // 에러가 터지면 어쨋든 문제가 잇는 것.
        Assertions.assertEquals("43214321", savedAuthor.getPassword()); // 에러가 터지면 어쨋든 문제가 잇는 것.
                                    //비밀번호 일부러 잘못 넣으면 에러남ㅇㅋ?

//        내가 짜다가 실패한거
//        AuthorUpdateReqDto authorUpdateReqDto = new AuthorUpdateReqDto();
//
//        authorUpdateReqDto.setName("hong777");
//        authorUpdateReqDto.setPassword("777777777777");
//
//        authorService.authorUpdate(authorUpdateReqDto,authorUpdateReqDto);
//        Assertions.assertEquals(authorUpdateReqDto.getName(), authorUpdateReqDto.getPassword());
    }

    //findAll 검증
    @Test
    public void findAllTest(){
        //내가 짠거
//        for (int i = 0; i < 3; i++) {
//
//            AuthorSaveReqDto authorSaveReqDto = new AuthorSaveReqDto(
//                    "hong111"+i, "hong111"+i+"@daum.net","12341234", Role.ADMIN // 비밀번호 예외 처리 잇음.
//            );
//            Author author = authorService.authorCreate(authorSaveReqDto);
//
//        }
//        // authorList를 조회한 후 저장한 개수와 저장된 개수 비교
//        Assertions.assertEquals(3,  authorService.authorList().size()); // 에러가 터지면 어쨋든 문제가 잇는 것.


        int size = authorService.authorList().size();

        AuthorSaveReqDto authorSaveReqDto1 = new AuthorSaveReqDto(
                "hong111", "hg111@daum.net","12341234", Role.ADMIN // 비밀번호 예외 처리 잇음.
        );
        Author author = authorService.authorCreate(authorSaveReqDto1);

        AuthorSaveReqDto authorSaveReqDto2 = new AuthorSaveReqDto(
                "hong111", "h23231@daum.net","12341234", Role.ADMIN // 비밀번호 예외 처리 잇음.
        );
        Author author2 = authorService.authorCreate(authorSaveReqDto2);

        AuthorSaveReqDto authorSaveReqDto3 = new AuthorSaveReqDto(
                "hong111", "2351@daum.net","12341234", Role.ADMIN // 비밀번호 예외 처리 잇음.
        );
        Author author3= authorService.authorCreate(authorSaveReqDto3);

        Assertions.assertEquals(size + 3, authorService.authorList().size());

    }



}
