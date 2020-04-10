package htp.controller.converters;

import htp.controller.request.ApplicationResult;
import htp.domain.dictionaries.LoanType;
import htp.domain.model.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationResultConverter extends GenericConverter<Application, ApplicationResult> {
    @Override
    public ApplicationResult convert(Application application) {
        ApplicationResult applicationResult = new ApplicationResult();
        applicationResult.setApplicationId(application.getApplicationId());
        applicationResult.setCreationDate(application.getCreationDate());
        applicationResult.setLoanType(LoanType.getNameLoanType(application.getLoanType()));
        applicationResult.setProductCode(application.getProductCode());
        applicationResult.setLoanAmount(application.getLoanAmount());
        applicationResult.setDecision(application.getDecision().toString());
        applicationResult.setFinalAmount(application.getFinalAmount());
        applicationResult.setPayment(application.getPayment());
        applicationResult.setStatus(application.getStatus().toString());
        return applicationResult;
    }
}
