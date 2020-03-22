package htp.entities.front_entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFront {

    @NotNull
    private Long productCode;

    @Size(min = 1, max = 200)
    private String productName;

    @PositiveOrZero
    private Double interestRate;

    @PositiveOrZero
    private Integer loanTerm;

    @PositiveOrZero
    private Long minAmount;

    @PositiveOrZero
    private Long maxAmount;
}
