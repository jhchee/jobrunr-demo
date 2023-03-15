package jhchee.github.jobrunrdemo.configs;

import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JobRunrConfig {
    private final DataSource dataSource;

    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        PostgresStorageProvider storageProvider = new PostgresStorageProvider(dataSource);
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }
}
