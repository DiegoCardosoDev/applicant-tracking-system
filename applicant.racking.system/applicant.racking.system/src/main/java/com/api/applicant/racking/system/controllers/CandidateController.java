package com.api.applicant.racking.system.controllers;


import com.api.applicant.racking.system.dto.requests.CandidateRequest;
import com.api.applicant.racking.system.dto.responses.CandidateDetailResponse;
import com.api.applicant.racking.system.dto.responses.CandidateResponse;
import com.api.applicant.racking.system.services.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/candidate", produces = {"application/json"})
@RequiredArgsConstructor
public class CandidateController {


    private final CandidateService candidateDetailService;
    private final CandidateService candidateService;


    @PostMapping ("/save")
    public ResponseEntity<CandidateResponse> createTechnologyStack(@RequestBody @Valid CandidateRequest candidateRequest) {
        CandidateResponse createdTechnologyStack = candidateService.createCandidate(candidateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTechnologyStack);
    }

    @GetMapping("/list")
    public List<CandidateResponse> getAllCandidatesWithStacks() {
        return candidateService.getAllCandidatesWithStacks();
    }


    @GetMapping("/search/{id}")
    public CandidateResponse getById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @GetMapping("/search")
    public List<CandidateResponse> getCandidatesByName(@RequestParam String name) {
        return candidateService.getCandidateByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.candidateService.deleteCandidate(id);
    }
}
