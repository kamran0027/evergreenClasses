package com.evergreenClasses.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResultForm {

    private Long id;
    private String subjectName;
    private double marksObtained;
    private double fullMarks;
    private String testDate;
    private Long studentId;

    


}
