package com.beyond.board.author.service;


import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// readOnly 역할이 뭐지?
// 조회 작업 시 readOnly 설정하면 성능이 향상.
// 다만 저장 작업시에는 Transactional을 메서드 단위로 달아줘야한다. 아니면 조회 위에다가 따로 readonly만 붙이던지
// 선택적으로 가져가겟ㄷ.
@Transactional
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    @Autowired // 싱글톤 객체를 주입(DI -> 디펜젼시 인젝션)
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author authorCreate(AuthorSaveReqDto requestDto) {
        // Author를 반환하는 형식으로 하는 이유? 혹시나 쓸일 있으면 써라~라는 이유로 설정한 것 이다.
//        아이디 이메일정보 이런게 다 드러나니까 주지는 않을 것 이다.
        if (authorRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 Email 입니다");
        }

        Author savedAuthor = requestDto.toEntity();
        authorRepository.save(savedAuthor); // 50라인에서 예외가 터지면 kim씨로 저장이 안된다.

        return savedAuthor;
    }

    public List<AuthorListResDto> authorList () {

        List<AuthorListResDto> authorListResDtos = new ArrayList<>(); // author 객체를 받을 수 있는 AuthorResDto 리스트를 선언

        // authorRepository에 가서 findALL해서 모든 회원 목록을 가져온다
        // 그 회원 목록 객체를 저장할 수 있는 Author에 자료구조 List로 받아준다.
        List<Author> authorList = authorRepository.findAll();

        for (Author author : authorList) {

            // 위에서 받아온 Author 객체를 fromEntity 메서드를 통해서 조립한다.
            // 담아온 객체를 fromEntity 메서드로 조립해서 Res에 add한다 ㅇㅋ?
            authorListResDtos.add(author.fromEntity());

        }
        return authorListResDtos;
    }

    public AuthorDetailDto authorDetail(Long id) {

//      왜 Optional? findById의
        Optional<Author> findAuthor = authorRepository.findById(id);
        Author author = findAuthor.orElseThrow(() -> new EntityNotFoundException("없는 회원 입니다."));

        AuthorDetailDto authorDetailDto = author.detailFromEntity();

        return authorDetailDto;
    }

    public Author authorFindByEmail(String email){

        Author author = authorRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("없는 회원 입니다."));

        return author;
    }
}
