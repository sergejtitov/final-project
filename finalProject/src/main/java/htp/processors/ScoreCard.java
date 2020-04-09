package htp.processors;

import htp.domain.wrappers.ApplicantWrapper;

public interface ScoreCard {
    Integer calculateScore(ApplicantWrapper applicantWrapper);
    Integer getDeclinedScore ();
}
