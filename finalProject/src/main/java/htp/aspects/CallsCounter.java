package htp.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class CallsCounter {
    private static AtomicLong counter = new AtomicLong(0);

    @AfterReturning("execution(* htp.dao.*.*(..))")
    private void countCalls() {
        counter.incrementAndGet();
    }

    public AtomicLong getCounter() {
        return counter;
    }
}
