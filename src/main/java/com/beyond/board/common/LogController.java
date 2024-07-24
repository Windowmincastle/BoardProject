package com.beyond.board.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 어노테이션을 통한 로거 선언 방법
@RestController
public class LogController {

    /*
    sout의 문제
    1 : 성능이 떨어진다.
    2 : 분류 작업을 할수가없다.
    3 :
     */

    private static final Logger logger
            = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log/test1")
    public String logtest1(){
//      기존의 로그 방식 System.out.println();
        System.out.println("println 로그 입니다.");

        logger.info("slfj4를 통한 info 로그 입니다");

        logger.error(" slfj4를 통한 error 로그");


        return "성공";


    }

    //에러가 발생하면 에러로그만 따로 보관하고싶지않겟냐
    //


}
