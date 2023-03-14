package jhchee.github.jobrunrdemo.jobs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jobrunr.scheduling.cron.Cron;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum JobHolder {
    SimpleJob(SimpleJob.class, Cron.every5minutes());
    private Class<? extends JobI> clazz;
    private String cron;

    public String getJobId() {
        return this.clazz.getSimpleName();
    }
}
