package com.beyond.board.author;


import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AuthorServiceMockTest {

// 가짜 객체 Mocking 이라는 개념이 나온다.
//  저장 및 detail 조회
//  update 검증
//  findAll 검증
    @Autowired
    AuthorService authorService;
//    @Autowired
//    AuthorRepository authorRepository;

    //가짜 객체를 만드는 작업을 목킹이라한다.
    @MockBean
    AuthorRepository authorRepository;


    @Test
    public void findAuthorDetailTest(){

        // 목빈과 목 객체를 만드는 것에 포인트를 두자,
        // 모킹이라고 하는게 가짜 repo를 활용해서 가짜 레포의 return값을 통제할 수 있다를 기억하자

        /*
        db의 진짜 실제값이랑 비교하는게 아니라 가짜 db를 만들고 서비스 로직을 검증한다.
        db의 정합성을 검증하는게 아니다. 진짜 DB를 조회하지 않아도 된다.
         */

        AuthorSaveReqDto authorSaveReqDto
                = new AuthorSaveReqDto("test1", "test1@google.com", "12341234", Role.ADMIN);
        Author author1 = authorService.authorCreate(authorSaveReqDto);

//        //이렇게 가짜 디비를 만들어버리는 것 이다. id 1번으로 조회하면 항상 이 객체가 가짜 객체로 조회된다.
//        Author myAuthor = Author.builder()
//                .id(1L)
//                .name("test")
//                .email("test@naver.com")
//                .build();

        AuthorDetailDto authorDetailDto = authorService.authorDetail(author1.getId());
//                                          findById랑 레포지토리쪽에 문제가 잇으면 어떡할거냐?
//                                          우린 지금 서비스 로직 검증하러 온건데 ? 지금은 레포 검증이 아니기 때문에
//
//        Author savedAuthor = authorRepository.findById(author1.getId()).orElseThrow(
//                ()-> new EntityNotFoundException("not found")
//        );


        //가짜 객체를 만들어서 조회하겠다. thenReturn에서 옵셔널로 리턴해야함. 서비스랑 repo를 분리하기위해 이 작업을 한다.
        Mockito.when(authorRepository.findById(author1.getId())).thenReturn(Optional.of(author1));

        Author savedAuthor = authorRepository.findById(author1.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        Assertions.assertEquals(authorDetailDto.getEmail(),savedAuthor.getEmail());

    }

}
