package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

// DIに登録される
@Repository
public class HelloRepository {

    // JDBC接続用クラス
    // インスタンスを取得する
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findOne(int id) {

        String query = "SELECT "
                + " employee_id,"
                + " employee_name,"
                + " age "
                + "FROM employee "
                + "WHERE employee_id=?";

        // SELECT
        Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);

        return employee;
    }
}
