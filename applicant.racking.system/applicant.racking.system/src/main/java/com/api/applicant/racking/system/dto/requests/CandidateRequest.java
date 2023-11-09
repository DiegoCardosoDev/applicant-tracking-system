package com.api.applicant.racking.system.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CandidateRequest {


    @NotNull (message = "nome obrigatório")
    @Size (min = 1, max = 255)
    private String name;

    @NotNull(message = "informe a idade")
    private Integer age;

    @Email (message = "insira uma email valido!")
    private String email;

    @NotBlank (message = "informe a atuação")
    private String position_professional;

    @NotBlank(message = "informe a senioridade")
    private String seniority;

    private String certification;

    @NotBlank(message = "informe a graduação")
    private String graduation;

    @NotBlank(message = "insira a preferencia de atuação")
    private String preference_of_atuation;

    private String linkedin_profile;


    @NotBlank
    private String locality;

}
