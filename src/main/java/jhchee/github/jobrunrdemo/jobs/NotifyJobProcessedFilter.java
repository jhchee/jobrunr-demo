package jhchee.github.jobrunrdemo.jobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.filters.ApplyStateFilter;
import org.jobrunr.jobs.filters.JobServerFilter;
import org.jobrunr.jobs.states.JobState;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotifyJobProcessedFilter implements ApplyStateFilter, JobServerFilter {

    @Override
    public void onStateApplied(Job job, JobState oldState, JobState newState) {
    }

    @Override
    public void onProcessed(Job job) {
        System.out.println(job.getJobState().getName().name());
    }
}