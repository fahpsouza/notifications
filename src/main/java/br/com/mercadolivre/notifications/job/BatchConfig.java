package br.com.mercadolivre.notifications.job;

//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    @Bean
//    public Job notificationJob() {
//        return jobBuilderFactory.get("notificationJob")
//                .incrementer(new RunIdIncrementer())
//                .start(notificationStep())
//                .build();
//    }
//
//    @Bean
//    public Step notificationStep() {
//        return stepBuilderFactory.get("notificationStep")
//                .<Notification, Notification>chunk(10)
//                .reader(() -> (Notification) notificationRepository.findAll().iterator())
//                .processor(notification -> {
//                    // Processa a notificação, por exemplo, logar ou enviar
//                    return notification;
//                })
//                .writer(notifications -> {
//                    notifications.forEach(System.out::println); // Simula o envio
//                })
//                .build();
//    }
//}

