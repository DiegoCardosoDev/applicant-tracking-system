package com.api.applicant.racking.system.dto.responses;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseResponse {

    private String course_name;
    private String course_instituition;
    private Integer duration;

    public CourseResponse(String course_name,String course_instituition,Integer duration){
        this.course_name = course_name;
        this.course_instituition = course_instituition;
        this.duration = duration;
    }
}
