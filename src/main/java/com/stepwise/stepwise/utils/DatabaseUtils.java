package com.stepwise.stepwise.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseUtils {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public <T> Page<T> pageQuery(
            String sql,
            MapSqlParameterSource mapSqlParameterSource,
            Integer page,
            Integer perPage,
            Class<T> Clazz) {

        List<Sort.Order> orders = new ArrayList<>();

        StringBuilder sbCount = new StringBuilder();

        sbCount.append(" SELECT COUNT(A.*) FROM ( ").append(sql).append(" ) A ");

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT * FROM ( SELECT row_number() OVER () AS ROWNUMQ, Q.* FROM ( ")
                .append(sql)
                .append(
                        " ) Q ) A WHERE A.ROWNUMQ > (:page * :perPage) - :perPage AND A.ROWNUMQ <= (:page * :perPage) ");

        PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(orders));

        int totalRows =
                namedParameterJdbcTemplate.queryForObject(
                        sbCount.toString(), mapSqlParameterSource, Integer.class);

        mapSqlParameterSource.addValue("page", page).addValue("perPage", perPage);

        List<T> dtos =
                namedParameterJdbcTemplate.query(
                        sb.toString(), mapSqlParameterSource, new BeanPropertyRowMapper(Clazz));

        Page<T> resultPage = new PageImpl<T>(dtos, pageRequest, totalRows);

        return resultPage;
    }



}
