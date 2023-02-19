package jhchee.github.jobrunrdemo.service;

import lombok.RequiredArgsConstructor;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.states.StateName;
import org.jobrunr.storage.PageRequest;
import org.jobrunr.storage.StorageProvider;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final StorageProvider storageProvider;

    // Unconventional way to get job updated between intervals because library author includes this API in Pro version
    public List<Job> getJobsUpdatedBetween(StateName stateName, Instant start, Instant end) {
        PageRequest pageRequest = PageRequest.descOnUpdatedAt(0, 50);
        List<Job> results = new ArrayList<>();

        boolean hasNext;
        do {
            List<Job> currentPage = storageProvider.getJobs(stateName, end, pageRequest);
            List<Job> inRange = currentPage.stream()
                                           .filter(j -> j.getUpdatedAt().equals(start) || j.getUpdatedAt().isAfter(start))
                                           .toList();
            results.addAll(inRange);
            // hasNext = true if in range is not empty
            hasNext = !inRange.isEmpty();
            pageRequest = pageRequest.nextPage();
        } while (hasNext);

        return results;
    }
}
