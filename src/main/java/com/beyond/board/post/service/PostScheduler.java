//package com.beyond.board.post.service;
//
//
//import com.beyond.board.post.domain.Post;
//import com.beyond.board.post.repository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import java.time.LocalDateTime;
//
////전체 주석 처리 7월 22일 batch실습을 위해서 주석 처리했음.
//@Component
//public class PostScheduler {
//
//    //서비스는 특정 레이어인데 그런 용도라기보단 싱글톤 객체로 등록을 하거라 컴포넌트를 붙일거임
//    private final PostRepository postRepository;
//
//    @Autowired
//    public PostScheduler(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
//
//    //아래 스케쥴의 cron부는 각 자리마다 "초 분 시간 일 월 요일"을 의미한다
//    //예를 들어) * * * * * * : 매일 매분 매초마다 시간이 돌아간다 서버에 부하가 많이 생긴다
//    //예를 들어) 0 0 * * * * : 매일 0분 0초마다 -> 1시간에 1번 해석을 해야한다
//    //예를 들어) 0 0 11 * * * : 매일 11시에
//    //예를 들어) 0 0/1 * * * * : 매일 매시 1분마다 -> 한시간에 60번 돌아간다.
//    //예를 들어) 0  1 * * * * : 매일 매시 1분에 -> 한시간에 1번
//    //쓸때마다 헷갈리니까 달달 외우지말고 찾아서 써라
//    @Scheduled( cron = "0 0/1 * * * *")
//    @Transactional
//    public  void postSchedule(){
//        System.out.println("=======예약 글 쓰기 스케쥴러 시작 =============");
//        // unpaged 페이징 처리 안 하겟다.
//        Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(), "Y");
//        for (Post p : posts) {
//            if (p.getAppointmentTime().isBefore(LocalDateTime.now())) {
//
//                p.updateAppointment("N"); // 더지체킹에 의해서 save를 별도로 하지 않아도 된다. 변경사항은 알아서 해준다.
//
//
//            }
//        }
//    }
//
//}
