package it.nave.controller;

import it.nave.dto.DummyDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "dummy")
public class DummyController {
    private final JobLauncher jobLauncher;
    private final Job job;

    public DummyController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dummyJob(@RequestBody DummyDto dummy) throws Exception {
        jobLauncher.run(job, new JobParametersBuilder()
                .addJobParameter("dummy-dto", dummy, DummyDto.class)
                .toJobParameters());
    }
}
