package com.api.applicant.racking.system.controllers;


import com.api.applicant.racking.system.dto.requests.JobsRequest;
import com.api.applicant.racking.system.dto.responses.JobsResponse;
import com.api.applicant.racking.system.services.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/jobs", produces = {"application/json"})
@RequiredArgsConstructor
public class JobsController {

    private final JobsService jobsService;


    @PostMapping ( "/save" )
    public ResponseEntity<JobsResponse> createTechnologyStack(@RequestBody JobsRequest jobsRequest){

        JobsResponse jobsResponse = jobsService.createNewJob(jobsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobsResponse);
    }

    @GetMapping ( "/list" )
    public List<JobsResponse> getAllJobs(){
        return jobsService.getAllJobs();
    }

    @GetMapping ( "/active" )
    public List<JobsResponse> getAllJobsActiveTrue(){
        return jobsService.getAllActiveJobs();
    }

}
