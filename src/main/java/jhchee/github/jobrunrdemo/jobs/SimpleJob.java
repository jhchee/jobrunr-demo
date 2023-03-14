package jhchee.github.jobrunrdemo.jobs;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.context.JobContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class SimpleJob implements JobI {
    @Override
    public void execute(JobContext context, Map<String, Object> metadata) throws Exception {
        log.info("Hello: {}", metadata.get("name"));
        for (int i = 0; i < 100; i++) {
            context.saveMetadata("progress", i);
        }
    }
}
