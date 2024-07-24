package com.beyond.board.common;

import com.beyond.board.author.service.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity // spring 시큐리티 설정을 커스텀마이징하기 위함
@EnableGlobalMethodSecurity( prePostEnabled = true )//pre : 사전 , post : 사후 인증검사
public class SecurityConfig {

    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
// csrf공격에 대한 설정은 하지 않겠다 라는 의미. restful에서는 사용 안하는 공격이라함.
// mvc 패턴에선 막아야한다고 하는듯
                .csrf().disable()
                .authorizeRequests()
                //아래 antMatchers는 인증 제외
                .antMatchers("/", "/author/register", "/author/login-screen")
                .permitAll()
                //그외 요청은 모두 인증 필요
                .anyRequest()
                .authenticated()
                .and()
                //만약 세션 로그인이 아니라 토큰 로그인 일 경우
                //.sessionManagement().sessionCreationPolicy(sessioncreationPolicy)
                .formLogin()
                    .loginPage("/author/login-screen")
                // doLogin메서드는 스프링에서 미리 구현
                    .loginProcessingUrl("/doLogin")
                //다만, doLogin에 넘겨줄 email과,password 속성명은 별도로 지정해야한다.
                    .usernameParameter("email")
                    .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())
                .and()
                    .logout()//로그아웃
                //security에서 구현된 doLogout기능을 사용하겟다
                    .logoutUrl("/doLogout")
                .and()
                .build();

    }
}
