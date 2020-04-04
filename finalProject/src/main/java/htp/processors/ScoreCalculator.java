package htp.processors;

import htp.domain.wrappers.ApplicantWrapper;

public interface ScoreCalculator {

    Integer getScore (ApplicantWrapper applicantWrapper, Integer productCode);

}
