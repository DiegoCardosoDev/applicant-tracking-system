package com.api.applicant.racking.system.services;

import com.api.applicant.racking.system.dto.requests.CandidateRequest;
import com.api.applicant.racking.system.dto.responses.CandidateSimpleResponse;
import com.api.applicant.racking.system.dto.responses.CandidateResponse;
import com.api.applicant.racking.system.entities.CandidateEntity;
import com.api.applicant.racking.system.exeptions.BusinessException;
import com.api.applicant.racking.system.exeptions.UnprocessableEntityException;
import com.api.applicant.racking.system.mappers.CandidateMapper;
import com.api.applicant.racking.system.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateRepository;



    public CandidateResponse createCandidate(CandidateRequest candidateRequest){
        CandidateEntity candidateEntity = candidateMapper.mapToRequest(candidateRequest);
        candidateEntity = candidateRepository.save(candidateEntity);
        return candidateMapper.mapToResponse(candidateEntity);
    }

    //listar todos
    public List<CandidateResponse> getAllCandidatesWithStacks() {
        List<CandidateEntity> candidateEntities = candidateRepository.findAll();
        return candidateEntities.stream()
                .map(candidateMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public CandidateResponse getCandidateById(Long id){
        try{
            CandidateEntity candidateEntity = candidateRepository.findById(id)
                    .orElseThrow(() -> new UnprocessableEntityException("Candidate not found with id: " + id));
            return candidateMapper.mapToResponse(candidateEntity);
        }catch ( Exception e ){
            throw new BusinessException(format("Error search Candidate  with id: " + id),e);
        }

    }

    public List<CandidateResponse> getCandidateByName(String name){

        try {
            List<CandidateEntity> candidates = candidateRepository.findByNameContainingIgnoreCase(name);
            if(candidates.isEmpty()){
                throw new UnprocessableEntityException("not found with name{}"+ name);
            }
            return candidates.stream()
                    .map(candidateMapper::mapToResponse)
                    .collect(Collectors.toList());
        }catch ( Exception e ){
            throw new BusinessException(format("not found candidate with name = %s",name),e);
        }
    }

    public List<CandidateSimpleResponse> getAllCandidates() {
        List<CandidateEntity> candidates = candidateRepository.findAll();
        return candidates.stream()
                .map(CandidateSimpleResponse::new)
                .collect(Collectors.toList());
    }

    public void deleteCandidate(Long id){
        try{
            if ( !candidateRepository.existsById(id) ) {
                throw new UnprocessableEntityException("Candidate not found with id: " + id);
            }else {
                candidateRepository.deleteById(id);
            }
        }catch ( Exception e  ){
            throw new BusinessException(format("Error delete Candidate  with id: " + id),e);
        }
    }

}
