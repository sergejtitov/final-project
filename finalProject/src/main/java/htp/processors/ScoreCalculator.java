package htp.processors;

import htp.entities.wrappers.ApplicantWrapper;

public interface ScoreCalculator {

    Integer getScore (ApplicantWrapper applicantWrapper, Integer productCode);

}
