package jhchee.github.jobrunrdemo.jobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.filters.JobServerFilter;

@Slf4j
public class NotifyJobProcessedFilter implements JobServerFilter {
    @Override
    public void onProcessed(Job job) {
        System.out.println(job.getJobState().getName().name());
    }
}