package htp.config.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CallsCounter {
    private long counter;

    @AfterReturning("execution(* htp.dao.DAOinterfaces.*.*(..))")
    private void countCalls() {
        counter++;
    }

    public long getCounter() {
        return counter;
    }
}