package com.micro.jobms.job.impl;

import com.micro.jobms.job.Job;
import com.micro.jobms.job.JobRepository;
import com.micro.jobms.job.JobService;
import com.micro.jobms.job.dto.JobDTO;
import com.micro.jobms.job.external.Company;
import com.micro.jobms.job.external.Review;
import com.micro.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    //prvate final List<Job> jobs = new ArrayList<Job>();
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) { //constructor autowired at runtime
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private JobDTO convertToDto(Job job) {
        Company company = restTemplate.getForObject(  //getForObject is useful for a single object
                "http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(),
                Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange( // exchange is useful for generic collection
                "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });
        List<Review> reviews = reviewResponse.getBody();
        return JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
