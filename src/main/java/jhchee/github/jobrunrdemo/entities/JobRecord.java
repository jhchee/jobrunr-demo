package jhchee.github.jobrunrdemo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class JobRecord {
    @Id
    @Column(name = "id")
    String id;
    @Column(name = "last_offset")
    Instant last_offset;
}
