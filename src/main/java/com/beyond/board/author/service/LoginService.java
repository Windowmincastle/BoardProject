package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
// 여기를 찾으러 옴
public class LoginService implements UserDetailsService {

    //생성자패턴해도 무방하다.
    @Autowired
    private AuthorService authorService;


    //이것만 있으면 끝이다 로그인은 ㅇㅋ?
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorService.authorFindByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+author.getRole().toString()));
        return new User(author.getEmail(), author.getPassword(), authorities);
    }
}
