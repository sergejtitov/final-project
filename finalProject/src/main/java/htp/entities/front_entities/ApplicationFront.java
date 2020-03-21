package htp.entities.front_entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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

    @Size(min = 3, max = 3)
    private String currency;

    private List<ApplicantFront> applicants;
}
