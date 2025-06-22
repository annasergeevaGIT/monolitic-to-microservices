package com.micro.jobms.job;

import com.micro.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs") // adds /jobs to every URL
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}") //dynamic naming
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDTO = jobService.getJobById(id);
        if(jobDTO != null)
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>( "Job created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //@RequestMapping(value = "jobs/{id}", method = RequestMethod.PUT) use on the method level
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }
}
