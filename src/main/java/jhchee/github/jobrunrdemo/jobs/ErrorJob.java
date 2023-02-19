package jhchee.github.jobrunrdemo.jobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.context.JobContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Job that throws error to test failure recovery.
 */
@Slf4j
@Component
public class ErrorJob implements JobI {
    @Override
    public void execute(JobContext context, Map<String, Object> jobParameters) throws Exception {
        throw new RuntimeException();
    }
}
