package com.api.applicant.racking.system.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseRequest {

    @NotNull (message = "nome obrigatório")
    @Size (min = 1, max = 255)
    private String course_name;

    @NotNull(message = "nome da instituition")
    private String course_instituition;

    @NotNull(message = "insira a duração")
    private Integer duration;
}
