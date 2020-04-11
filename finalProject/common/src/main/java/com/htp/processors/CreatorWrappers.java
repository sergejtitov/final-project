package com.htp.processors;

import com.htp.domain.model.Applicant;
import com.htp.domain.model.Application;
import com.htp.domain.model.CreditInfo;
import com.htp.domain.wrappers.ApplicantWrapper;
import com.htp.domain.wrappers.ApplicationWrapper;
import com.htp.repository.spring_data.CreditInfoDataRepository;
import com.htp.repository.spring_data.ProductDataRepository;


import java.util.List;

public interface CreatorWrappers {

    ApplicantWrapper createApplicantWrapper(Applicant applicant, List<CreditInfo> creditInfos);

    List<ApplicantWrapper> createListApplicantWrapper(Application application, CreditInfoDataRepository creditInfoService);

    ApplicationWrapper createApplicationWrapper (Application application, CreditInfoDataRepository creditInfoService, ProductDataRepository productService);
}
