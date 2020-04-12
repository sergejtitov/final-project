package com.htp.services.search_criteria;


import com.htp.domain.model.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ApplicationSpecificationBuilder {
    public static final int ZERO = 0;
    private List<SearchCriteria> params;

    public ApplicationSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public ApplicationSpecificationBuilder add(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Application> build(){
        if (params.size() == ZERO){
            return null;
        }

        List<Specification> specs = params.stream()
                .map(ApplicationSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(ZERO);

        for (int i = 1; i < params.size(); i++){
            result = params.get(i) != null
                    ? Specification.where(result)
                        .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
