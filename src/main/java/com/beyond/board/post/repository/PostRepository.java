package com.beyond.board.post.repository;


import com.beyond.board.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //Page<Post> : List<Post> + 해당 요소의 Page 정보
    //페이지 정보는 0번부터 시작된다
    //Pageable : PageNumber(몇번페이지) , Size(페이지마다 몇 페이지씩-default 20) , 정렬 조건도 들어갈 수 있다.
    //page 재선언 , Pageable 매개변수를 줘야한다.
    Page<Post> findAll(Pageable pageable);
    Page<Post> findByAppointment(Pageable pageable,String apt); //알아서 처리해준다

    // JPQL 문법.
    // 네이밍룰을 통한 방식이 아닌 메서드 생성.
    // select p.*, a.* from post p left join author a on p.author_id=a.id;
    @Query("select p from Post p left join fetch p.author")
    List<Post> findAllFetch();

    // fetch 가 아닌 그냥 left join 이라면 ?
    // select p.* from post p left join author a on p.author_id=a.id;
    // => a 를 안 가져 옴 ! -> left join 을 뭐하러 하나요..?
    // author 객체를 통한 조건문으로 post 를 filtering 할 때 사용함. -> 이름이 hong 인 사람의 post 를 가져오겠다!
    // -> N+1 문제가 똑같이 발생. -> 해결 방법이 lazy다.
    @Query("select p from Post p left join p.author")
    List<Post> findAllNOFetch();


}
