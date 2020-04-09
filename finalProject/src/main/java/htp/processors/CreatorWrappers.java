package htp.processors;

import htp.dao.spring_data.CreditInfoDataRepository;
import htp.dao.spring_data.ProductDataRepository;
import htp.domain.model.Applicant;
import htp.domain.model.Application;
import htp.domain.model.CreditInfo;
import htp.domain.wrappers.ApplicantWrapper;
import htp.domain.wrappers.ApplicationWrapper;

import java.util.List;

public interface CreatorWrappers {

    ApplicantWrapper createApplicantWrapper(Applicant applicant, List<CreditInfo> creditInfos);

    List<ApplicantWrapper> createListApplicantWrapper(Application application, CreditInfoDataRepository creditInfoService);

    ApplicationWrapper createApplicationWrapper (Application application, CreditInfoDataRepository creditInfoService, ProductDataRepository productService);
}
