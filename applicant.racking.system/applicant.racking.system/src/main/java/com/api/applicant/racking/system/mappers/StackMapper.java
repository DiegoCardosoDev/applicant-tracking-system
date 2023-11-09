package com.api.applicant.racking.system.mappers;


import com.api.applicant.racking.system.dto.responses.TechnologyStackResponse;
import com.api.applicant.racking.system.entities.TechnologyStackEntity;
import com.api.applicant.racking.system.repositories.TechnologyStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StackMapper {

    private final TechnologyStackRepository technologyStackRepository;

    public TechnologyStackResponse mapStackResponse(TechnologyStackEntity stackEntity){
        TechnologyStackResponse technologyStackResponse = new TechnologyStackResponse();

        technologyStackResponse.setId(stackEntity.getId());
        technologyStackResponse.setTechnology_name(stackEntity.getTechnology_name());
        return technologyStackResponse;
    }

}
