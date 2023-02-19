package jhchee.github.jobrunrdemo.repositories;

import jhchee.github.jobrunrdemo.entities.JobRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRecordRepository extends JpaRepository<JobRecord, String> {
}
