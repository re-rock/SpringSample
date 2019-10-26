package com.example.demo.login.domain.Repository.jdbc;

import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDaoJdbcImpl2")
public class UserDaoJdbcImpl2 extends UserDaoJdbcImpl {

  @Autowired // JdbcTemplateはデフォルトでBean定義がされている
  private JdbcTemplate jdbc;

  @Override // Userテーブルのデータを1件取得
  public User selectOne(String userId) {

    String sql = "SELECT * FROM m_user WHERE user_id = ?";

    // RowMapperの生成
    UserRowMapper rowMapper = new UserRowMapper();

    // SQLの実行
    return jdbc.queryForObject(sql, rowMapper, userId);
  }

  @Override // Userテーブルの全データを取得
  public List<User> selectMany() {

    String sql = "SELECT * FROM m_user";

    // RowMapperの生成
    RowMapper<User> rowMapper = new UserRowMapper();

    return jdbc.query(sql, rowMapper);
  }
}
