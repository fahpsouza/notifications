package br.com.mercadolivre.notifications.service;


import br.com.mercadolivre.notifications.dto.BatchDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//public class BatchService {

//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job notificationJob;

//    public void launchNotificationJob(@Valid BatchDto dto) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("time", dto.jobRunEveryFewSeconds())
//                .toJobParameters();
//        jobLauncher.run(notificationJob, jobParameters);
//    }
//}
