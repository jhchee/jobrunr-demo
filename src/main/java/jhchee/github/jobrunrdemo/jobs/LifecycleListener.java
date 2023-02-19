package jhchee.github.jobrunrdemo.jobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.filters.JobServerFilter;

import java.time.Instant;

/**
 * JobRunr filter == Quartz filter
 */
@Slf4j
public class LifecycleListener implements JobServerFilter {
    @Override
    public void onProcessingSucceeded(Job job) {
        String recurringId = job.getRecurringJobId().orElse(null);
        String className = job.getJobDetails().getClassName();
        Instant createdAt = job.getCreatedAt();
        log.info("Processed recurringId={}, className={}, createdAt={}", recurringId, className, createdAt);
    }

    @Override
    public void onProcessing(Job job) {
        String recurringId = job.getRecurringJobId().orElse(null);
        String className = job.getJobDetails().getClassName();
        Instant createdAt = job.getCreatedAt();
        log.info("Processing recurringId={}, className={}, createdAt={}", recurringId, className, createdAt);
    }

    @Override
    public void onProcessingFailed(Job job, Exception e) {
        // TODO: Send Slack message when failed
    }

    @Override
    public void onFailedAfterRetries(Job job) {
    }
}