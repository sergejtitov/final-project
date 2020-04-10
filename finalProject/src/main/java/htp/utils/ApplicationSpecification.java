package htp.utils;

import htp.controller.request.SearchCriteria;
import htp.domain.model.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Slf4j
@Data
@AllArgsConstructor
public class ApplicationSpecification implements Specification<Application> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder builder){
        if (criteria.getOperation().equalsIgnoreCase(">")){
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")){
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")){
            if (root.get(criteria.getKey()).getJavaType() == String.class){
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue()+"%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
