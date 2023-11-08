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
@Table (name = "enterprise")
@Entity
public class EnterpriseEntity {


    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String position_professional;

    @OneToMany(mappedBy = "enterprise")
    private List<JobsEntity> jobs;

}
