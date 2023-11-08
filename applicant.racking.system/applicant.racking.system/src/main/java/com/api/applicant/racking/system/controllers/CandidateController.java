package com.api.applicant.racking.system.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/candidate", produces = {"application/json"})
@RequiredArgsConstructor
public class CandidateController {
}
