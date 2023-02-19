package jhchee.github.jobrunrdemo.jobs;

import jhchee.github.jobrunrdemo.service.JobService;
import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.states.StateName;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 *
 * Generate daily report about job statues.
 */
@Component
@RequiredArgsConstructor
public class ReportJob implements JobI {
    private final JobService jobService;

    @Override
    public void execute(JobContext jobContext, Map<String, Object> jobParameters) throws Exception {
        Instant now = Instant.now();
        Instant start = now.minus(Duration.ofDays(1));
        List<Job> succeededJobs = jobService.getJobsUpdatedBetween(StateName.SUCCEEDED, start, now);
        List<Job> failedJobs = jobService.getJobsUpdatedBetween(StateName.FAILED, start, now);

        // TODO: Send Slack report on daily job status.
    }
}
