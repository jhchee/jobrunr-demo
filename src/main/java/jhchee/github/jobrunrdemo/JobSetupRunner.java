package jhchee.github.jobrunrdemo;

import jhchee.github.jobrunrdemo.jobs.NotifyJobProcessedFilter;
import org.jobrunr.server.BackgroundJobServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobSetupRunner implements ApplicationRunner {
    @Autowired
    BackgroundJobServer backgroundJobServer;

    @Override
    public void run(ApplicationArguments args) {
        backgroundJobServer.setJobFilters(List.of(new NotifyJobProcessedFilter()));
    }
}
