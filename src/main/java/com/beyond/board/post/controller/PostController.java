package com.beyond.board.post.controller;


import com.beyond.board.post.dto.PostDetailDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/create")
    public String postCreate(@RequestBody PostSaveReqDto postSaveReqDto) {

        postService.postCreate(postSaveReqDto);
        return "OK";

    }

    @GetMapping("/post/list")
    public List<PostListResDto> postListResDtos() {
        return postService.postList();
    }

    @GetMapping("/post/detail/{id}")
    public PostDetailDto postDetailDto(@PathVariable Long id) {
        return null;
    }
}
