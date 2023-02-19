package jhchee.github.jobrunrdemo.controllers;


import io.swagger.v3.oas.annotations.Operation;
import jhchee.github.jobrunrdemo.jobs.JobHolder;
import jhchee.github.jobrunrdemo.jobs.JobI;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobBuilder;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.RecurringJobBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobScheduler jobScheduler;
    private final ApplicationContext applicationContext;

    @Operation(description = "Delete job.")
    @PostMapping(value = "/delete")
    public ResponseEntity<String> deleteJob(@RequestBody JobHolder holder) {
        jobScheduler.delete(holder.getJobId());
        return ResponseEntity.ok("Deleted");
    }

    @Operation(description = "Enqueue adhoc job.")
    @PostMapping(value = "/enqueue")
    public ResponseEntity<String> enqueueJob(@RequestBody JobHolder holder) {
        Map<String, Object> jobParameters = Map.of("name", "James", "age", 1);

        JobI jobClazz = applicationContext.getBean(holder.getClazz());
        jobScheduler.create(
                JobBuilder.aJob()
                          .withAmountOfRetries(3)
                          .withLabels("adhoc")
                          .withDetails(
                                  () -> jobClazz.execute(JobContext.Null, jobParameters)
                          )
        );
        return ResponseEntity.ok("Enqueued");
    }

    @Operation(description = "Schedule recurring job.")
    @PostMapping(value = "/schedule")
    public ResponseEntity<String> scheduleJob(@RequestBody JobHolder holder) {
        Map<String, Object> jobParameters = Map.of("name", "James", "age", 1);

        JobI jobClazz = applicationContext.getBean(holder.getClazz());
        jobScheduler.createRecurrently(
                RecurringJobBuilder.aRecurringJob()
                                   .withId(jobClazz.getClass().getSimpleName())
                                   .withAmountOfRetries(3)
                                   .withCron(holder.getCron())
                                   .withLabels("cron")
                                   .withDetails(
                                           () -> jobClazz.execute(JobContext.Null, jobParameters)
                                   )
        );
        return ResponseEntity.ok("Scheduled");
    }
}
