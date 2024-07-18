package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.PostDetailDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.repository.PostRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
/*
서비스 또는 레포지토리를 오토와이어 할지는 상황에 따라 다르나
서비스 레벨에서 코드가 고도화 돼있고 코드의 중복이 심할 경우 서비스 레이어를 오토와이어 하는게 나은 듯
그러나 순환참조가 발생할 경우에는 레포지토리를 오토와이어드
 */
    private final AuthorService authorService; // 순환참조가 막아지는게 아니라 컴파일 타임 에러가 나니까 찾을 수 있어서 장점이라 볼 수 있다.

    // 이렇게 하면 문제는 뭔가?
    @Autowired
    public PostService(PostRepository postRepository,AuthorService authorService){
        this.postRepository = postRepository;
        this.authorService = authorService;
    }
/*
authorservice에서 author 객체를 찾아 post의 toEntity에 넘기고
jpa가 author 객체에서 author_id를 찾아 db에는 author_id가 들어간다.
 */
    public Post postCreate(PostSaveReqDto saveDto){
        Author author = authorService.authorFindByEmail(
                saveDto.getEmail());

        Post post = postRepository.save(saveDto.toEntity(author));
        return post;

    }

    public List<PostListResDto> postList(){

        List<Post> posts = postRepository.findAll();
        List<PostListResDto> postListResDtos = new ArrayList<>();

        for (Post p : posts) {
            postListResDtos.add(p.fromEntity());
        }

        return postListResDtos;
    }

    public PostDetailDto postDetail(Long id) {
        Post post = postRepository
                .findById(id).orElseThrow(()->new EntityNotFoundException("없는 회원입니다."));
        return post.detFromEntity();
    }

}
