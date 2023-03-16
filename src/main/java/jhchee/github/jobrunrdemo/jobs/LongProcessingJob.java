package jhchee.github.jobrunrdemo.jobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.context.JobContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LongProcessingJob implements JobI {
    @Override
    public void execute(JobContext context, Map<String, Object> jobParameters) throws Exception {
        log.info("Hello: {}", jobParameters.get("name"));
        for (int i = 0; i < 100; i++) {
            TimeUnit.SECONDS.sleep(1);
            context.progressBar(i+1);
            context.saveMetadata("progress", i);
        }
    }
}
