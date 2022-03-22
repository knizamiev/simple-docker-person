package ru.example.docker.dao;

import liquibase.pro.packaged.E;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import ru.example.docker.dao.common.AbstractDAO;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import ru.example.docker.dao.common.ExtendedBeanPropertySqlParameterSource;
import ru.example.docker.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    public UserDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void addUser(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update("insert into users(" +
                        "id, name, family, patronymic, birthday)" +
                        "values (nextval('users_seq'), :name, :family, :patronymic, :birthday)",
                new ExtendedBeanPropertySqlParameterSource(user),
                keyHolder,
                new String[]{"id"});

        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public User findUser(Long id) {
        return selectOne("select * from users where id = :id", map("id", id), 
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("select * from users order by id", map(), new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void updateUser(User user) {
        ExtendedBeanPropertySqlParameterSource source = new ExtendedBeanPropertySqlParameterSource(user);
        jdbcTemplate.update("update users set name = :name, family = :family, patronymic = :patronymic," +
                " birthday = :birthday where id = :id", source);
    }

    @Override
    public void deleteUser(long id) {
        jdbcTemplate.update("delete from users where id = :id", map("id", id));
    }
}
