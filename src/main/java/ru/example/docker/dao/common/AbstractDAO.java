package ru.example.docker.dao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractDAO {

    protected final static Logger LOGGER = LoggerFactory.getLogger(ru.example.docker.dao.common.AbstractDAO.class);

    protected final NamedParameterJdbcTemplate jdbcTemplate;

    protected Set<String> sortableColumns;

    public AbstractDAO(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        JdbcOperations operations = jdbcTemplate.getJdbcOperations();
        if (operations instanceof JdbcTemplate) {
            ((JdbcTemplate) operations).setFetchSize(1000);
        }
    }
    
    protected static Map<String, Object> map(Object ... keysAndValues) {
        Map<String, Object> result = new HashMap<>();
        for (int i=0; i<keysAndValues.length; i+=2) {
            result.put((String) keysAndValues[i], keysAndValues[i+1]);
        }
        return result;
    }

    protected <T> T selectOne(String sql, Map<String, Object> params, RowMapper<T> mapper) {
        List<T> items = jdbcTemplate.query(sql, params, mapper);
        return items.isEmpty() ? null : items.get(0);
    }

//    protected <T> T selectOne(String sql, Map<String, Object> params, ResultSetExtractor<List<T>> extractor) {
//        List<T> items = jdbcTemplate.query(sql, params, extractor);
//        return items.isEmpty() ? null : items.get(0);
//    }

}