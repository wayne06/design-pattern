package inaction.idempotence.prepare.mode2;

import inaction.idempotence.prepare.Idempotence;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 在 AOP 切面中处理幂等
 */
@Aspect
public class IdempotenceSupportAdvice {

    @Autowired
    private Idempotence idempotence;

    @Pointcut("@annotation(inaction.idempotence.prepare.mode2.IdempotenceRequired)")
    public void controllerPointcut() {

    }

    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 从 HTTP header 中获取幂等号 idempotenceId
        String idempotenceId = "";

        // 前置操作
        boolean existed = idempotence.check(idempotenceId);
        if (existed) {
            // 两种处理方式
            // 1. 查询 order，并且返回
            // 2. 返回 duplication operation exception
        }
        idempotence.record(idempotenceId);

        Object result = joinPoint.proceed();
        return result;
    }

}
