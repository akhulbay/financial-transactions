package com.example.silkpaytestassignment.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceAspect {

    @Pointcut("execution(public * com.example.silkpaytestassignment.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Pointcut("execution(public * com.example.silkpaytestassignment.service.*Service.create(*))")
    public void anyCreateServiceMethod() {
    }

    @Pointcut("execution(public * com.example.silkpaytestassignment.service.WalletService.findBalanceById(*))")
    public void findBalanceByIdMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod() " +
                    "&& args(id) " +
                    "&& target(service)", argNames = "id,service")
    public void addLoggingBeforeInFindByIdMethod(Object id, Object service) {
        log.info("invoked findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(pointcut = "anyFindByIdServiceMethod() " +
                            "&& target(service)",
            returning = "result", argNames = "service,result")
    public void addLoggingAfterReturningInFindByIdMethod(Object service, Object result) {
        log.info("after returning - invoked findById method in class {}, with result {}", service, result);
    }

    @AfterThrowing(pointcut = "anyFindByIdServiceMethod() " +
                           "&& target(service)",
            throwing = "ex")
    public void addLoggingAfterThrowingInFindByIdMethod(Object service, Throwable ex) {
        log.info("after throwing - invoked findById method in class {}, with error {}:{}",
                service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "anyCreateServiceMethod() " +
                    "&& args(dto) " +
                    "&& target(service)", argNames = "dto,service")
    public void addLoggingBeforeInCreateMethod(Object dto, Object service) {
        log.info("invoked create method in class {}, with dto {}", service, dto);
    }

    @AfterReturning(pointcut = "anyCreateServiceMethod() " +
                            "&& target(service)",
            returning = "result",
            argNames = "service,result")
    public void addLoggingAfterReturningInCreateMethod(Object service, Object result) {
        log.info("after returning - invoked create method in class {}, with result {}", service, result);
    }

    @AfterThrowing(pointcut = "anyCreateServiceMethod() " +
                           "&& target(service)",
            throwing = "ex",
            argNames = "service,ex")
    public void addLoggingAfterThrowingInCreateMethod(Object service, Throwable ex) {
        log.info("after throwing - invoked create method in class {}, with error {}:{}",
                service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "findBalanceByIdMethod() " +
                    "&& args(id) " +
                    "&& target(service)", argNames = "id,service")
    public void addLoggingBeforeInFindBalanceByIdMethod(Object id, Object service) {
        log.info("invoked findBalanceById method in class {}, with id {}", service, id);
    }

    @AfterReturning(pointcut = "findBalanceByIdMethod() " +
                            "&& target(service)",
            returning = "result",
            argNames = "service,result")
    public void addLoggingAfterReturningInFindBalanceByIdMethod(Object service, Object result) {
        log.info("after returning - invoked findBalanceById method in class {}, with result {}", service, result);
    }

    @AfterThrowing(pointcut = "findBalanceByIdMethod() " +
                           "&& target(service)",
            throwing = "ex",
            argNames = "service,ex")
    public void addLoggingAfterThrowingInFindBalanceByIdMethod(Object service, Throwable ex) {
        log.info("after throwing - invoked findBalanceById method in class {}, with error {}:{}",
                service, ex.getClass(), ex.getMessage());
    }
}
