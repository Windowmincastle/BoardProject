package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.repository.PostRepository;
import com.beyond.board.post.dto.PostUpdateReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        SecurityContextHolder securityContextHolder = new SecurityContextHolder();

        Author author = authorService.authorFindByEmail(email); //여기에 사용자 이메일 넣으면 끝

        // 7 22 글 예약 작성 기능하면서 수정햇음.
        String appointment = null;
        LocalDateTime appointmentTime = null;

        if (saveDto.getAppointment().equals("Y") && !saveDto.getAppointmentTime().isEmpty()) {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            appointmentTime = LocalDateTime.parse(saveDto.getAppointmentTime(), dateTimeFormatter);
            //현재시간보다 이전시각을 선택하면 에러발생
            LocalDateTime now = LocalDateTime.now();

            if (appointmentTime.isBefore(now)){
                throw new IllegalArgumentException("시간 입력이 잘못됐습니다.");
            }

        }
        System.out.println(saveDto); //찍어보기
        Post post = postRepository.save(saveDto.toEntity(author,appointmentTime));

        return post;
    }

    public Page<PostListResDto> postList(Pageable pageable){
//        List<Post> posts = postRepository.findAll();
//        List<Post> posts = postRepository.findAllFetch(); // Fetch join 적용 후 코드 수정
//        List<PostListResDto> postListResDtos = new ArrayList<>();
//        for (Post post : posts) { // author 는 post 에 담겨있어요.
//            postListResDtos.add(post.listFromEntity());
//        }
        //위 코드 주석처리하고 아래 코드 삽입 7-22
//        Page<Post> posts = postRepository.findAll(pageable);
        Page<Post> posts = postRepository.findByAppointment(pageable,"N");
        Page<PostListResDto> postListResDtos = posts.map(
                a -> a.listFromEntity());
        return postListResDtos;
    }

    // 페이지 자체가 내부적으로 알아서 리스트 형태로 리턴이 되기 떄문에 감쌀필요없다.
    // 페이지 객체로 감싸는데 페이지 객체가 리스트로 되어있다.
    // 매개변수 넘겨야한다 Pageable , 나중에 페이징처리할때 이 형식 그대로 써도 될거같다.
    public Page<PostListResDto> postListPage(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostListResDto> postListResDtos = posts.map(
                a -> a.listFromEntity()); // map 형태는 요소 하나하나를 순회하면서 새로운 요소를 만들어내는데
        //요소가 페이지다 보니까 페이지 요소로 만들어주는 것 이다.
        return postListResDtos;  //반환타입이 Page<List>가 되도록 커스텀을 해줘야 한다.
    }

    public PostDetResDto postDetail(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("찾을 수 없습니다."));
        PostDetResDto postDetResDto = post.detFromEntity();
        return postDetResDto;
    }

    public void postDelete(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("찾을 수 없습니다."));

        if (!post.getAuthor().getEmail().equals(email)) {
            throw new IllegalArgumentException("본인의 게시글만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);

    }

    @Transactional
    public Post postUpdate(Long id, PostUpdateReqDto postUpdateReqDto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException(" 찾을 수 없습니다."));

        if (!post.getAuthor().getEmail().equals(email)) {
            throw new IllegalArgumentException("본인의 게시글만 수정할 수 있습니다.");
        }

        post.updatePost(postUpdateReqDto);
        Post updateReult = postRepository.save(post);

        return updateReult;
    }



}
