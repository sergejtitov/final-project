package htp.controller.converters;

import org.springframework.core.convert.converter.Converter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericConverter <S,T> implements Converter<S, T> {
    @PersistenceContext
    protected EntityManager entityManager;

    public void setEntityManager (EntityManager entityManager){
        this.entityManager = entityManager;
    }
}
