package com.beyond.board.author.repository;

import com.beyond.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository <Author,Long> {

    // JPA가 쿼리 만들어주는데 룰이 몇 가지 있다.
    // findBy 컬럼명의 규칙으로 자동으로 where 조건문을 사용한 쿼리 생성 , 규칙이니까 외워라
    // 그외 : findByNameAndEmail , findByNameOrEmail
    // findBy(Age)Between(int start, int end)
    // findBy(Age)LessThan(int age)
    // findBy(Age)GreaterThan(int age)
    //     findAllOrderByEmail();
    Optional<Author> findByEmail(String email);
//    List<Author> findAllOrderByEmailil(); // 이거 주석 안해서 에러 터졋엇다.

}

