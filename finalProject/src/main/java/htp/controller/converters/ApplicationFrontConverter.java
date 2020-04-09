package htp.controller.converters;

import htp.controller.request.ApplicationFront;
import htp.domain.model.Application;
import htp.utils.Parsers;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ApplicationFrontConverter extends GenericConverter<ApplicationFront, Application> {

    @Override
    public Application convert(ApplicationFront request) {
        Application application = new Application();
        application.setCreationDate(new Timestamp(System.currentTimeMillis()));
        application.setLoanType(request.getLoanType());
        application.setProductCode(request.getProductCode());
        application.setLoanAmount(request.getLoanAmount());
        application.setApplicants(Parsers.createSetApplicants(request.getApplicants(), application));
        return application;
    }
}
