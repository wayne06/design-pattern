package opensourced.mybatis.demo2;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

/**
 * MyBatis Plugin 使用举例：统计应用中每个 SQL 的执行耗
 * 只需要定义一个 SqlCostTimeInterceptor 类，让它实现 MyBatis 的 Interceptor 接口，并且，在 MyBatis 的全局配置文件中，简单声明一下这个插件就可以了
 *
 * 不管是拦截器、过滤器还是插件，都需要明确地标明拦截的目标方法。@Intercepts 注解实际上就是起了这个作用。
 * 其中，@Intercepts 注解又可以嵌套 @Signature 注解。一个 @Signature 注解标明一个要拦截的目标方法。
 * 如果要拦截多个方法，可以像例子中那样，编写多条 @Signature 注解。
 * @Signature 注解包含三个元素：type、method、args。其中，type 指明要拦截的类、method 指明方法名、args 指明方法的参数列表。
 * 通过指定这三个元素，就能完全确定一个要拦截的方法。
 *
 * 默认情况下，MyBatis Plugin 允许拦截的方法有下面这样几个：
 * Executor: update, query, flushStatements, commit, rollback, getTransaction, close, isClosed
 * ParameterHandler: getParameterObject, setParameters
 * ResultSetHandler: handleResultSets, handleOutputParameters
 * StatementHandler: prepare, parameterize, batch, update, query
 *
 * MyBatis 底层是通过 Executor 类来执行 SQL 的。Executor 类会创建 StatementHandler、ParameterHandler、ResultSetHandler 三个对象，
 * 并且，首先使用 ParameterHandler 设置 SQL 中的占位符参数，然后使用 StatementHandler 执行 SQL 语句，最后使用 ResultSetHandler 封装执行结果。
 * 所以，我们只需要拦截 Executor、ParameterHandler、ResultSetHandler、StatementHandler 这几个类的方法，基本上就能满足我们对整个 SQL 执行流程的拦截了。
 *
 * 实际上，除了统计 SQL 的执行耗时，利用 MyBatis Plugin，我们还可以做很多事情，比如分库分表、自动分页、数据脱敏、加密解密等等
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
public class SqlCostTimeInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlCostTimeInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) target;
        try {
            return invocation.proceed();
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            LOGGER.info("Executing of SQL [ {} ] cost [ {} ms]", sql, costTime);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("Config info of plugin : " + properties);
    }
}
