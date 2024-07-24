//package com.beyond.board.post.service;
//
//
//import com.beyond.board.post.domain.Post;
//import com.beyond.board.post.repository.PostRepository;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.time.LocalDateTime;
//
//// batch를 돌리기 위한 스케쥴러를 정의한다.
//@Configuration
//public class PostJobConfiguration {
//    @Autowired
//    JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    private PostRepository postRepository;
//
//    public Job executeJob(){
//        return jobBuilderFactory
//                .get("executeJob")
//                .start(firstStep())
//                .next(secondStep()).build();
//    }
//
//    @Bean
//    public Step firstStep(){
//        return stepBuilderFactory.get("firstStep")
//                .tasklet (((stepContribution, chunkContext) -> {
//                    System.out.println("예약 글쓰기 batch task1 시작");
//                    Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(), "Y");
//                    for (Post p : posts) {
//                        if (p.getAppointmentTime().isBefore(LocalDateTime.now())) {
//                            p.updateAppointment("N"); // 더지체킹에 의해서 save를 별도로 하지 않아도 된다. 변경사항은 알아서 해준다.
//                        }
//                    }
//
//                    System.out.println("예약 글쓰기 batch task2 종료");
//
//                    return RepeatStatus.FINISHED;
//                })).build();
//    }
//
//
//    @Bean
//    public Step secondStep(){
//        return stepBuilderFactory.get("firstStep")
//                .tasklet (((stepContribution, chunkContext) -> {
//                    System.out.println("예약 글쓰기 batch task2 시작");
//                    System.out.println("예약 글쓰기 batch task2 종료");
//
//                    return RepeatStatus.FINISHED;
//                })).build();
//    }
//}
