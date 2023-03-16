package jhchee.github.jobrunrdemo.jobs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jobrunr.scheduling.cron.Cron;

/**
 * Store job reference for dynamic scheduling.
 */
@AllArgsConstructor
@Getter
public enum JobHolder {
    SimpleJob(SimpleJob.class, Cron.every15seconds()),
    LongProcessingJob(LongProcessingJob.class, Cron.every5minutes()),
    ErrorJob(ErrorJob.class, Cron.every15seconds()),
    ReportJob(ReportJob.class, null);
    private final Class<? extends JobI> clazz;
    private final String cron;

    public String getJobId() {
        return this.clazz.getSimpleName();
    }
}
