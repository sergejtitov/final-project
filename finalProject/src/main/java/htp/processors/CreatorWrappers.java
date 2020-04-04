package htp.processors;

import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.domain.model.Applicant;
import htp.domain.model.Application;
import htp.domain.model.CreditInfo;
import htp.domain.wrappers.ApplicantWrapper;
import htp.domain.wrappers.ApplicationWrapper;

import java.util.List;

public interface CreatorWrappers {

    ApplicantWrapper createApplicantWrapper(Applicant applicant, List<CreditInfo> creditInfos);

    List<ApplicantWrapper> createListApplicantWrapper(Application application, CreditInfoRepository creditInfoService);

    ApplicationWrapper createApplicationWrapper (Application application, CreditInfoRepository creditInfoService, ProductRepository productService);
}
