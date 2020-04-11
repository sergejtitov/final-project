package com.htp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFront {
    @NotNull
    private Integer loanType;

    @NotNull
    private Integer productCode;

    @PositiveOrZero
    private Double loanAmount;

    private List<ApplicantFront> applicants;
}
