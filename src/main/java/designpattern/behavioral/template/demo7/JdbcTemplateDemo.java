package designpattern.behavioral.template.demo7;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Spring 提供了 JdbcTemplate，对 JDBC 进一步封装，来简化数据库编程。
 *
 * 使用 JdbcTemplate 查询用户信息，我们只需要编写跟这个业务有关的代码，其中包括，查询用户的 SQL 语句、查询结果与 User 对象之间的映射关系。
 * 其他流程性质的代码都封装在了 JdbcTemplate 类中，不需要我们每次都重新编写
 *
 * JdbcTemplate 通过回调的机制，将不变的执行流程抽离出来，放到模板方法 execute() 中，将可变的部分设计成回调 StatementCallback，由用户来定制。
 * query() 函数是对 execute() 函数的二次封装，让接口用起来更加方便。
 *
 */
public class JdbcTemplateDemo {

    private JdbcTemplate jdbcTemplate;

    public User queryUser(long id) {
        String sql = "select * from user where id=" + id;
        return jdbcTemplate.query(sql, new UserRowMapper()).get(0);
    }

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setTelephone(resultSet.getString("telephone"));
            return user;
        }
    }
}
