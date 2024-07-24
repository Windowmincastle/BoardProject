package com.beyond.board.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig {

    // 패 스 워 드 만 드 는 방 법, 여기에 암호화 기술이 다 들어가잇는거니까 우리는 가져다가 쓰면된다.
    @Bean
    public PasswordEncoder makePassword(){
        return PasswordEncoderFactories
                .createDelegatingPasswordEncoder();
    }


}
