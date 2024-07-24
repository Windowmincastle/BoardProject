//package com.beyond.board.common;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//

// ****************** 테스트 끝나고 계속 로그 찍혀서 정신없어서 일부러 주석처리 했음.

////aop코드임을 명시
//@Aspect // AOP 선언
//@Component
//@Slf4j
//public class AopLogService {
//
//    //중간에 있는 미들웨어다. 컨트롤러에 들어가기전에 낚아채는 것.
//    /*
//    크게 종류가 2가지 있다.
//
//     */
//
////   aop의 대상(공통화의 대상)이 되는 controller, service 등의 위치를 명시
//    @Pointcut("within(@org.springframework.stereotype.Controller *)") // 모든 컨트롤러를 어노테이션 대상으로 하겠다.
//    public void controllerPointCut(){
//
//    }
//
//    // joinPoint란? 사용자가 실행하려고 하는 코드를 의미하고, 위에서 정의한 PointCut을 의미한다
//    // 방법1 Around를 통해 컨트롤러에 걸쳐져있는 사용 패턴이다.
//    @Around("controllerPointCut()") // Before After 테스트를 위해서 전체 주석처리 햇음.
//    public Object controllerLogger(ProceedingJoinPoint joinPoint){
//
//        log.info("Aop start");
//        log.info("Method 명 : " + joinPoint.getSignature().getName());
//
//        //직접 HttpServletRequest 객체를 꺼내는 법 . 서블릿객체 주입 못 받아서 이렇게 함
//        //컨트롤레 레이어에서는 바로 받을 수 있다.
//        ServletRequestAttributes attributes
//                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest(); //이렇게 쓸일은 여기밖에 없을것이니 외우지말길
//
//        log.info("HTTP 메서드" + request.getMethod()); //사용자 요청은 request 안에 있다.
//        Map<String,String[]> parameterMap = request.getParameterMap();
//        log.info("user inputs : " + parameterMap);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        ObjectNode objectNode = objectMapper.valueToTree(parameterMap);
//        log.info("user inputs : " + objectNode);
//
//        Object result = null;
//        try {
//            //사용자가 실행하고자 하는 코드 실행부다
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//        log.info("Aop end");
//
//        return result;
//
//    }
//
//    @Before("controllerPointCut()")
//    public void beforeController(JoinPoint joinPoint) {
//
//        log.info("Aop start");
//        log.info("Method 명 : " + joinPoint.getSignature().getName());
//
//        ServletRequestAttributes attributes
//                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        log.info("HTTP 메서드" + request.getMethod());
//
//    }
//
//
//    @After("controllerPointCut()")
//    public void afterController( ) {
//
//        log.info("Aop end");
//
//    }
//
//}
