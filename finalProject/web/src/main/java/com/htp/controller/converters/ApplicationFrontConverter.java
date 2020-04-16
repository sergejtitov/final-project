package com.htp.controller.converters;

import com.htp.controller.request.ApplicationFront;
import com.htp.utils.Parsers;
import com.htp.domain.model.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
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
