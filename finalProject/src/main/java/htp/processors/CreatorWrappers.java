package htp.processors;

import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.Application;
import htp.entities.db_entities.CreditInfo;
import htp.entities.wrappers.ApplicantWrapper;
import htp.entities.wrappers.ApplicationWrapper;

import java.util.List;

public interface CreatorWrappers {

    ApplicantWrapper createApplicantWrapper(Applicant applicant, List<CreditInfo> creditInfos);

    List<ApplicantWrapper> createListApplicantWrapper(Application application, CreditInfoRepository creditInfoService);

    ApplicationWrapper createApplicationWrapper (Application application, CreditInfoRepository creditInfoService, ProductRepository productService);
}
