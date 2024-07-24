//package com.beyond.board.post.service;
//
//
//import com.beyond.board.post.domain.Post;
//import com.beyond.board.post.repository.PostRepository;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//// batch를 돌리기 위한 스케쥴러를 정의한다.
//@Component
//public class PostBatchScheduler {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired
//    PostJobConfiguration postJobConfiguration;
//
//    @Scheduled(cron = "0 0/1 * * * *")
//    private void batchSchedule(){
//
////        PostJobConfiguration postJobConfiguration = new PostJobConfiguration();
//
//        Map<String, JobParameter> configMap = new HashMap<>();
//        //현재시간을 매개변수로 밀리초로 받아서 System~~을 넣는다
//        configMap.put("time", new JobParameter(System.currentTimeMillis()));
//
//        JobParameters jobParameters = new JobParameters(configMap);
//
//        try {
//            //예외가 강제된다. 일단은 임시로 대충 처리햇다.
//            jobLauncher.run(postJobConfiguration.executeJob(), jobParameters);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
