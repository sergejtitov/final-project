package com.htp.domain.dictionaries;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Set;

@Slf4j
public enum Decision {
    ACCEPT("Accept"),
    DECLINE("Decline"),
    NOT_SET("Not set");
    private String decisionName;

    public static final Set<Decision> decisions = Collections.unmodifiableSet(Set.of(Decision.values()));

    Decision(String decisionName) {
        this.decisionName = decisionName;
    }

    public String getDecisionName() {
        return decisionName;
    }

    public static Decision findByName (String decisionName){
        for (Decision decision : decisions){
            if (decision.getDecisionName().equalsIgnoreCase(decisionName)){
                return decision;
            }
        }
        return NOT_SET;
    }

}
