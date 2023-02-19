package jhchee.github.jobrunrdemo;

import jhchee.github.jobrunrdemo.jobs.JobHolder;
import jhchee.github.jobrunrdemo.jobs.JobI;
import jhchee.github.jobrunrdemo.jobs.LifecycleListener;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.RecurringJobBuilder;
import org.jobrunr.server.BackgroundJobServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Schedule recurring jobs when application startup.
 */
@Component
@RequiredArgsConstructor
public class JobSetupRunner implements ApplicationRunner {
    private final BackgroundJobServer backgroundJobServer;
    private final JobScheduler jobScheduler;
    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) {
        backgroundJobServer.setJobFilters(List.of(new LifecycleListener()));
        for (JobHolder jobHolder : JobHolder.values()) {
            // don't schedule if cron expression is null
            if (jobHolder.getCron() == null) continue;

            // dummy payload
            Map<String, Object> jobParameters = Map.of("name", "James", "age", 1);

            // get bean declaration for this class
            JobI jobClazz = applicationContext.getBean(jobHolder.getClazz());
            jobScheduler.createRecurrently(
                    RecurringJobBuilder.aRecurringJob()
                                       .withId(jobHolder.getJobId())
                                       .withAmountOfRetries(3)
                                       .withCron(jobHolder.getCron())
                                       .withLabels("cron")
                                       .withDetails(
                                               () -> jobClazz.execute(JobContext.Null, jobParameters)
                                       )
            );
        }
    }
}
