package com.htp.domain.dictionaries;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Set;

@Slf4j
public enum Status {
    ACCEPT("Accept"),
    ISSUED("Issued"),
    FAILURE("Failure"),
    CUSTOMER_FAILURE("Customer_failure"),
    NOT_SET("Not set");
    private String statusName;
    public static final Set<Status> statuses = Collections.unmodifiableSet(Set.of(Status.values()));

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public static Status findByName(String statusName){
        for (Status status : statuses){
            if (status.getStatusName().equalsIgnoreCase(statusName)){
                return status;
            }
        }
        return NOT_SET;
    }
}
