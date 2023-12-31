package com.api.applicant.racking.system.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "stack")
@Entity
public class TechnologyStackEntity {


    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private  String technology_name;

    @ManyToMany (mappedBy = "stack")
    private List<CandidateEntity> candidates;
}
