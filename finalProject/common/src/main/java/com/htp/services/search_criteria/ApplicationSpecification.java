package com.htp.services.search_criteria;


import com.htp.domain.model.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
public class ApplicationSpecification implements Specification<Application> {
    public static final String GREATER = ">";
    public static final String LESS = "<";
    public static final String EQUAL= "=";
    public static final String LIKE = "%";
    public static final int ZERO = 0;

    private List<SearchCriteria> searchCriteriaList;

    public ApplicationSpecification() {
        searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria searchCriteria){
        searchCriteriaList.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : searchCriteriaList){
            if (criteria.getOperation().equalsIgnoreCase(GREATER)){
                predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            }
            else if (criteria.getOperation().equalsIgnoreCase(LESS)){
                predicates.add(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equalsIgnoreCase(EQUAL)){
                if (root.get(criteria.getKey()).getJavaType() == String.class){
                    predicates.add(criteriaBuilder.like(root.get(criteria.getKey()), LIKE + criteria.getValue()+LIKE));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                }
            }
        }
        return  criteriaBuilder.and(predicates.toArray(new Predicate[ZERO]));
    }
}
