package com.beyond.board.author.controller;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//SSR 방식 , 모델 방식
@Slf4j
@Controller
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String home() {
        return "common/home";
    }

    @GetMapping("/author/login-screen")
    public String authorLoginScreen(){
        return "/author/author_login_screen";
    }
    @GetMapping("/author/register")
    public String authorCreateScreen() {

        return "/author/author_register";
    }

    @PostMapping("/author/register")
    public String authorCreate(@ModelAttribute AuthorSaveReqDto dto) {
        authorService.authorCreate(dto);
        return "redirect:/author/list"; //리다이렉트 홈으로
    }

    @GetMapping("/author/list")
    public String authorList(Model model) {
        // 클라이언트가 List를 요청했고 컨트롤러는 서비스단에 해당 로직을 넘긴다.
        // 컨트롤러는 클라이언트의 요청을 ResponseDto로 조립해서 돌려 줄 것
        // model로 맵핑해서 화면에 보여줄 것 이다.
        // ResponseDto에 서비스단에서 받아온 Author 리스트를 받아서 dtos에 저장
        List<AuthorListResDto> dtos = authorService.authorList();
        model.addAttribute("authorList", dtos);

        return "/author/author_list";
    }

    @GetMapping("/author/detail/{id}")
    public String authorDetailList(@PathVariable Long id, Model model) {

        //log 찎어놓기
//        log.info("get요청이고, parmeter는 "+ id);
//        log.info("메서드 : authorDetailList");

        model.addAttribute("author", authorService.authorDetail(id));
        return "/author/author_detail";
    }


    @GetMapping("/author/delete/{id}")
    public String authorDelete(@PathVariable Long id, Model model) {




        authorService.authorDelete(id);
        return "redirect:/author/list";
    }

    @GetMapping("/author/update/{id}")
    public String authorupdateGet() {
        return "author/author_detail";
    }

    @PostMapping("/author/update/{id}")
    public String authorUpdatePost(@PathVariable Long id, AuthorUpdateReqDto authorUpdateReqDto){
        authorService.authorUpdate(id, authorUpdateReqDto);
        return "redirect:/author/detail/"+id;
    }



}