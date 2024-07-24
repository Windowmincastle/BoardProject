package com.beyond.board.post.controller;

import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import com.beyond.board.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/post")
@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }
    @GetMapping("/create")
    public String postCreateGet(PostSaveReqDto postSaveReqDto){
        return "/post/post_register";
    }

    @PostMapping("/create")
    public String postCreate(PostSaveReqDto postSaveReqDto){
        postService.postCreate(postSaveReqDto);
        return "redirect:/post/list";
    }

    @GetMapping("/list")
    public String postList(Model model,
                           @PageableDefault(//수정한곳 7-22
                                   size=10,sort = "createdTime",direction = Sort.Direction.DESC) Pageable pageable){




        model.addAttribute("postList", postService.postList(pageable));
        return "/post/post_list";
    }

    @GetMapping("/list/page")
    @ResponseBody //페이징처리 왜함? 서버부하 안 줄라고
    // Pageable 요청 방법 : 웹 서비스하면 페이징처리 그냥 무조건 하는거다 ㅇㅋ?
    // 요청 방법 -> localhost:8080/post/list/page?size=10&page=0
    // @PageableDefault(size=10,sort = ) 이걸 달아서 사용한다.
    public Page<PostListResDto> postListPage(
            @PageableDefault(
                    size=10,sort = "createdTime",direction = Sort.Direction.DESC) Pageable pageable){
        return postService.postListPage(pageable);//pageable 객체넘겨주기
    }

    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model) {

        //log 찎어놓기
//        log.info("get요청이고, parmeter는 "+ id);
//        log.info("메서드 : postList");

        model.addAttribute("post", postService.postDetail(id));
        return "/post/post_detail";
    }

    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable Long id){
        postService.postDelete(id);
        return "redirect:/post/list";
    }

    @GetMapping("/update/{id}")
    public String postUpdateGet(){
        return "post/post_detail";
    }

    @PostMapping("/update/{id}")
    public String postUpdatePost(@PathVariable Long id, PostUpdateReqDto postUpdateReqDto){
        postService.postUpdate(id, postUpdateReqDto);
        return "redirect:/post/detail/"+id;
    }

}
