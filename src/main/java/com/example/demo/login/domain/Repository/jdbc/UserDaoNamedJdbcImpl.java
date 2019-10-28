package com.example.demo.login.domain.Repository.jdbc;

import com.example.demo.login.domain.Repository.UserDao;
import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("UserDaoNamedJdbcImpl") // DIにBean名をセットする
public class UserDaoNamedJdbcImpl implements UserDao {

  @Autowired // JdbcTemplateはデフォルトでBean定義がされている
  private NamedParameterJdbcTemplate jdbc;

  @Override // Userテーブルの件数を取得
  public int count() {
    String sql = "SELECT COUNT(*) FROM m_user";

    // パラメータ生成
    SqlParameterSource params = new MapSqlParameterSource();

    // 全県取得してカウント
    return jdbc.queryForObject(sql, params, Integer.class);
  }

  @Override // Userテーブルにテータを1件Insert
  public int insertOne(User user) {

    // SQL文にキー名を指定
    String sql =
            "INSERT INTO m_user("
                    + " user_id,"
                    + " password,"
                    + " user_name,"
                    + " birthday,"
                    + " age,"
                    + " marriage,"
                    + " role)"
                    + " VALUES("
                    + " :userId,"
                    + " :password,"
                    + " :userName,"
                    + " :birthday,"
                    + " :age,"
                    + " :marriage,"
                    + " :role)";

    // パラメータの設定
    SqlParameterSource params =
            new MapSqlParameterSource()
                    .addValue("userId", user.getUserId())
                    .addValue("password", user.getPassword())
                    .addValue("userName", user.getUserName())
                    .addValue("birthday", user.getBirthday())
                    .addValue("age", user.getAge())
                    .addValue("marriage", user.isMarriage())
                    .addValue("role", user.getRole());

    return jdbc.update(sql, params);
  }

  @Override // Userテーブルのデータを1件取得
  public User selectOne(String userId) {

    String sql = "SELECT * FROM m_user WHERE user_id = :userId";
    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId);

    // RowMapper
    RowMapper<User> rowMapper = new UserRowMapper();

    return  jdbc.queryForObject(sql, params, rowMapper);
  }

  @Override // Userテーブルの全データを取得
  public List<User> selectMany() {

    String sql = "SELECT * FROM m_user";

    SqlParameterSource params = new MapSqlParameterSource();
    // SQL実行
    List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

    // 結果返却用の変数
    List<User> userList = new ArrayList<>();

    // 取得したデータを結果返却用のListに格納していく
    for (Map<String, Object> map : getList) {
      // Userインスタンスの生成
      User user = new User();

      // Userインスタンスに取得したデータをセットする
      user.setUserId((String) map.get("user_id"));
      user.setPassword((String) map.get("password"));
      user.setUserName((String) map.get("user_name"));
      user.setBirthday((Date) map.get("birthday"));
      user.setAge((Integer) map.get("age"));
      user.setMarriage((Boolean) map.get("marriage"));
      user.setRole((String) map.get("role"));

      // 結果返却用のListに追加
      userList.add(user);
    }
    return userList;
  }

  @Override // Userテーブルを1件更新
  public int updateOne(User user) {

    String sql =
            "UPDATE M_USER SET"
                    + " password = :password,"
                    + " user_name = :userName,"
                    + " birthday = :birthday,"
                    + " age = :age,"
                    + " marriage = :marriage"
                    + " WHERE user_id = :userId";

    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", user.getUserId())
            .addValue("password", user.getPassword())
            .addValue("userName", user.getUserName())
            .addValue("birthday", user.getBirthday())
            .addValue("age", user.getAge())
            .addValue("marriage", user.isMarriage());

    return jdbc.update(sql, params);
  }

  // Userテーブルを１件削除.
  @Override
  public int deleteOne(String userId) {

    String sql = "DELETE FROM m_user WHERE user_id = :userId";

    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId);

    int rowNumber = jdbc.update(sql, params);

    return rowNumber;
  }

  // SQL取得結果をサーバーにCSVで保存する
  @Override
  public void userCsvOut() {

    // M_USERテーブルのデータを全件取得するSQL
    String sql = "SELECT * FROM m_user";

    // ResultSetExtractorの生成\
    UserRowCallbackHandler handler = new UserRowCallbackHandler();

    // SQL実行＆CSV出力
    jdbc.query(sql, handler);
  }
}
