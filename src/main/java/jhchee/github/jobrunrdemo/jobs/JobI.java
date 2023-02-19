package jhchee.github.jobrunrdemo.jobs;

import org.jobrunr.jobs.context.JobContext;

import java.util.Map;

public interface JobI {
    void execute(JobContext jobContext, Map<String, Object> jobParameters) throws Exception;
}
