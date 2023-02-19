package jhchee.github.jobrunrdemo.jobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoJobs implements JobI {
    @Job(jobFilters = {NotifyJobProcessedFilter.class})
    @Recurring(cron = "*/10 * * * * *")
    @Override
    public void execute() throws Exception {
        log.info("executed");
    }
}
