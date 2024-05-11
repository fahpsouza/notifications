//package br.com.mercadolivre.notifications.controller;



//@RestController
//@RequestMapping("/api/batch")
//public class BatchController {
//
//    @Autowired
//    private BatchService batchService;

//    @PostMapping("/schedule/job")
//    public String handle() {
//        try {
//            batchService.launchNotificationJob(dto);
//            return "Batch job has been invoked";
//        } catch (Exception e) {
//            return "Failed to execute batch job";
//        }
//    }

//    @PostMapping("/schedule/job")
//    public ResponseEntity<String> scheduleJob(@RequestBody @Valid BatchDto dto) {
//        try {
////            batchService.launchNotificationJob(dto);
//            return ResponseEntity.ok("Batch job has been invoked!");
//        } catch (RuntimeException e) {
//            return ResponseEntity.internalServerError().body("Failed to execute batch job - RuntimeException");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//}
