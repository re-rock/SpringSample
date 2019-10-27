package com.example.demo.login.domain.Repository.jdbc;

import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDaoJdbcImpl4")
public class UserDaoJdbcImpl4 extends UserDaoJdbcImpl {

  @Autowired // JdbcTemplateはデフォルトでBean定義がされている
  private JdbcTemplate jdbc;

  @Override // Userテーブルの全データを取得
  public List<User> selectMany() {

    String sql = "SELECT * FROM m_user";

    // ResultSetExtractorの生成
    UserResultSetExtractor extractor = new UserResultSetExtractor();

    // execute
    return jdbc.query(sql, extractor);
  }
}
